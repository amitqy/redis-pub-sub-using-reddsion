package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import config.AppProperties;
import config.UserDetails;
import listener.RMapEntryCreatedListener;
import listener.RMapEntryExpiredListener;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ServletContext servletContext = getServletContext();
		AppProperties  appProperties   = (AppProperties)servletContext.getAttribute("dbProperties");
		Connection connection = appProperties.getConnection();
		response.getWriter().write(connection.toString());
		System.out.println("In login");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			RedissonClient redisClient =  appProperties.getRedissonClient();
			RMapCache<String, UserDetails> rMapCache = redisClient.getMapCache("userDetailsWithTTL");
			rMapCache.addListener(new RMapEntryCreatedListener(redisClient));
			rMapCache.addListener(new RMapEntryExpiredListener(redisClient));

		if(rMapCache.containsKey(userName)) {
			//System.out.println("Fetching from the Cache");
			request.setAttribute("loggedInUser",  userName);
			request.setAttribute("src", "redis cache");
			UserDetails userDetails = rMapCache.get(userName);
			RequestDispatcher dispatcher = userDetails.getPassword().equals(password)
					? request.getRequestDispatcher("html/RegisterSuccess.jsp") : request.getRequestDispatcher("html/AuthFailure.jsp");
			dispatcher.forward(request, response);
		}
		else {
			String sql = "select * from users where username = ? and password = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs =  stmt.executeQuery();
			String user;

			if(rs.next()) {
				user =  rs.getString("username");
				System.out.println("Correct credentials");
				request.setAttribute("loggedInUser",  userName);
				request.setAttribute("src", "database");
				rMapCache.putIfAbsent(userName, new UserDetails(rs.getString("password"), userName), 100, TimeUnit.SECONDS);
				RequestDispatcher dispatcher = request.getRequestDispatcher("html/RegisterSuccess.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("loggedInUser",  userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("html/AuthFailure.jsp");
				dispatcher.forward(request, response);
			}
		}

		} catch (SQLException e) {
				System.out.println("Exception occured while executing sql");
		}

		}

}
