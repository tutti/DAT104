package no.hib.dat104.lab2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/front")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Boolean TRUE = new Boolean(true);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset=\"utf-8\">");
		out.println("	<title>Brukerkonto</title>");
		out.println("	<link rel='stylesheet' href='style.css' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Login result</h1>");
		if (TRUE.equals(session.getAttribute("isLoggedIn"))) {
			out.println("<p>Logged in from <span class='red'>" + request.getHeader("Referer") + "</span></p>");
			out.println("<p>Username: <span class='red'>" + session.getAttribute("username") + "</span></p>");
			out.println("<p>Password: <span class='red'>" + session.getAttribute("password") + "</span></p>");
			out.println("<a href='logout'>Log out</a>");
		} else {
			out.println("<p>You are not logged in.</p>");
			out.println("<a href='loginform.html'>Log in</a>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
