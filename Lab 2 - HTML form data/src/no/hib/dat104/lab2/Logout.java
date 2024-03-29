package no.hib.dat104.lab2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Boolean TRUE = new Boolean(true);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if (!TRUE.equals(session.getAttribute("isLoggedIn"))) {
			// User is not logged in.
			response.sendRedirect(request.getContextPath() + "/front");
			return;
		}
		
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/front");
		
	}

}
