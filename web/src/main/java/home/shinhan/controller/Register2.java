package home.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.shinhan.diabroker.Model;

/**
 * Servlet implementation class Register2
 */
@WebServlet("/view/register2.do")
public class Register2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Model model = new Model();
		
		double carat = Double.parseDouble(request.getParameter("carat"));
		String cut = request.getParameter("cut");
		String color = request.getParameter("color");
		String clarity = request.getParameter("clarity");
		double depth = Double.parseDouble(request.getParameter("depth"));
		double table = Double.parseDouble(request.getParameter("table"));
		double x = Double.parseDouble(request.getParameter("x"));
		double y = Double.parseDouble(request.getParameter("y"));
		double z = Double.parseDouble(request.getParameter("z"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		String user_id = (String) session.getAttribute("user_id");
		int dia_id;
		while(true) {
			dia_id = (int)(Math.random() * 100000);
			if(model.dia_id_dup_chk(dia_id)) break;
		}
		
		String result;
		
		if(model.diaInsert(dia_id, carat, cut, color, clarity, depth, table, x, y, z, price, user_id) == 1) {
			result = "Registered.";
		} else result = "Registering Failed.";
		
		response.getWriter().print(result);
	}

}
