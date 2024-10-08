package home.shinhan.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns="/view/register.do", asyncSupported = true)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double[] weights = {5.27068728e+03, -9.41494605e+01, -6.56733910e+01, -1.04871690e+03,
				-2.61778240e+00, -4.02581884e+01, -5.51531479e+02, -9.89161013e+00,
				2.44255338e+02,  1.70266056e+02,  1.46901694e+02, -3.81837050e+03,
				1.51518457e+03, -1.47992407e+02, -1.11650240e+03,  7.74077768e+02,
				4.54186882e+02,  1.20318808e+03,  1.13622800e+03,  7.97700390e+02,
				6.17082417e+02,  5.60775695e+02,  3.47141856e+02, -1.45917654e+02,
				-6.31107943e+02, -1.54567476e+03};
		
		HashMap<String, Integer> object_matches = new HashMap<>();
		
		object_matches.put("Fair", 6);
		object_matches.put("Good", 7);
		object_matches.put("Ideal", 8);
		object_matches.put("Premium", 9);
		object_matches.put("Very Good", 10);
		
		object_matches.put("I1", 11);
		object_matches.put("IF", 12);
		object_matches.put("SI1", 13);
		object_matches.put("SI2", 14);
		object_matches.put("VS1", 15);
		object_matches.put("VS2", 16);
		object_matches.put("VVS1", 17);
		object_matches.put("VVS2", 18);
		
		object_matches.put("D", 19);
		object_matches.put("E", 20);
		object_matches.put("F", 21);
		object_matches.put("G", 22);
		object_matches.put("H", 23);
		object_matches.put("I", 24);
		object_matches.put("J", 25);
		
		String carat = request.getParameter("carat");
		String cut = request.getParameter("cut");
		String color = request.getParameter("color");
		String clarity = request.getParameter("clarity");
		String depth = request.getParameter("depth");
		String table = request.getParameter("table");
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String z = request.getParameter("z");
		
		double normalized_carat = (Double.parseDouble(carat)-0.7979397478679852)/0.47401124440538067;		
		double normalized_depth = (Double.parseDouble(depth)-61.74940489432624)/1.4326213188337733;		
		double normalized_table = (Double.parseDouble(table)-57.45718390804603)/2.234490562820938;		
		double normalized_x = (Double.parseDouble(x)-5.731157211716609)/1.1217607467924422;		
		double normalized_y = (Double.parseDouble(y)-5.734525954764462)/1.1421346741235396;		
		double normalized_z = (Double.parseDouble(z)-3.5387337782723316)/0.7056988469499964;		
		
		Double evaluated = normalized_carat*weights[0]+weights[object_matches.get(cut)]+weights[object_matches.get(color)]+weights[object_matches.get(clarity)]
				+normalized_depth*weights[1]+normalized_table*weights[2]+normalized_x*weights[3]+normalized_y*weights[4]+normalized_z*weights[5]+3374.4522550030997;
		
		evaluated = Math.abs(evaluated);
		
		String result = "$ " + String.valueOf((int)Math.round(evaluated));
		response.getWriter().print(result);
		
	}
}
