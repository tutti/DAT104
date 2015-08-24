import java.io.IOException;
import java.io.PrintWriter;
import lib.Frequency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/generate")
public class NameGenerator extends HttpServlet {
	private static final long serialVersionUID = 1446637653100121845L;
	private static Frequency names = new Frequency("pokemon.txt");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		int length;
		try {
			length = Integer.parseInt(request.getParameter("length"));
		} catch (Exception e) {
			length = 7;
		}
		
		
		String name = names.quadSample(length + 10).substring(10);
		out.println(name);
		
	}

}
