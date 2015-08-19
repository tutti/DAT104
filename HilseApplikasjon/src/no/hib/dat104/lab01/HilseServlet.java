package no.hib.dat104.lab01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HilseServlet
 */
@WebServlet("/HilseServlet")
public class HilseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date naa = new Date();
		String dato = DateFormat.getDateInstance(DateFormat.LONG).format(naa);
		response.setContentType("text/html; charset=utf-8");
		String navn = request.getParameter("navn");
		if (navn == null) {
			navn = "verden";
		}
		navn = new String(navn.getBytes("iso-8859-1"), "UTF-8");
		String test = "זרו"; 
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>HilseApplikasjon</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Velkommen " + navn + "</h1>");
		out.println("<p>" + test + "</p>");
		out.println("<p>Denne siden ble produsert " + dato + ".</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
