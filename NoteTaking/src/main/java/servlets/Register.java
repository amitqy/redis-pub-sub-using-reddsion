package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.AppProperties;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AppProperties  appProperties   = (AppProperties)getServletContext().getAttribute("dbProperties");
		Connection connection = appProperties.getConnection();
		response.getWriter().write(connection.toString());
		System.out.println("In login");
		response.getWriter().write(connection.toString());
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			String sql = "insert into users(username,password) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);

			int result = stmt.executeUpdate();
			if(result > 0) {
				System.out.println("User inserted into database");
				request.setAttribute("registeredUser",  userName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("html/RegisterSuccess.jsp");
				dispatcher.forward(request, response);
			}
			else {
				System.err.println("ERROR OCCURED WHILE INSERTING!");
			}
		} catch (SQLException e) {

		}

	}

}
