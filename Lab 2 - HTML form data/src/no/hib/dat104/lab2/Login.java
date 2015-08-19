package no.hib.dat104.lab2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Boolean TRUE = new Boolean(true);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if (TRUE.equals(session.getAttribute("isLoggedIn"))) {
			// User is already logged in.
			response.sendRedirect(request.getContextPath() + "/front");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		assert username != null : "No username supplied.";
		assert password != null : "No password supplied.";
		
		if (username == null || password == null) {
			// Username and/or password were not supplied.
			response.sendRedirect(request.getContextPath() + "/front");
			return;
		}
		
		session.setAttribute("isLoggedIn", true);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		response.sendRedirect(request.getContextPath() + "/front");
		
		// this is terrible practice what the hell
		
	}

}
