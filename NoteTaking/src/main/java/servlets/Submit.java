package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Submit
 */
@WebServlet("/proceed")
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		if(request.getParameter("action").equals("login")) {
			dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = request.getRequestDispatcher("/register");
			dispatcher.forward(request, response);
		}
	}

}
