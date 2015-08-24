package no.hib.dat104.lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fruity
 */
@WebServlet("/fruity")
public class Fruity extends HttpServlet {
	private List<String> hentFrukter(String filnavn) throws FileNotFoundException {
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/" + filnavn);
		List<String> r = new ArrayList<String>();
		Scanner s = new Scanner(new File(fullPath));
		while (s.hasNextLine()) {
			r.add(s.nextLine());
		}
		s.close();
		return r;
	}

	private static final long serialVersionUID = 1L;

	private HashMap<String, VoteOption> voteOptions;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Fruity() {
		super();
	}
	
	public void init() throws ServletException {
		super.init();

		voteOptions = new HashMap<String, VoteOption>();
		//String[] opts = { "Eple", "Pære", "Kiwi", "Banan" };
		try {
			List<String> opts = hentFrukter("fruits.txt");
			for (String s : opts) {
				voteOptions.put(s, new VoteOption(s));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset=\"utf-8\">");
		out.println("	<title>Fruktavstemming</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<form action='fruity' method='post'>");
		for (String s : voteOptions.keySet()) {
			out.println("	<input type='radio' name='frukt' value='" + s + "' />" + s + "<br />");
		}
		out.println("		<input type='submit' value='Stem' />");
		out.println("	</form>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		String stemme = new String(request.getParameter("frukt").getBytes(), "utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset=\"utf-8\">");
		out.println("	<title>Fruktavstemming</title>");
		out.println("</head>");
		out.println("<body>");
		if (voteOptions.containsKey(stemme)) {
			VoteOption stemmeObjekt = voteOptions.get(stemme);
			stemmeObjekt.addVote();
			out.println("Din stemme: " + stemme + "<br />");
			for (VoteOption vo : voteOptions.values()) {
				out.println(vo + "<br />");
			}
		} else {
			out.println("Feil: " + stemme);
		}
		out.println("</body>");
		out.println("</html>");
	}

}
