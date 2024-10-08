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
 * Servlet implementation class Todibs
 */
@WebServlet("/view/todibs.do")
public class Todibs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Todibs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Model model = new Model();
		
		String user_id = (String) session.getAttribute("user_id");
		int dia_id = Integer.parseInt(request.getParameter("dia_id"));
		
		String result;
		
		if(model.dibs_dup_chk(user_id, dia_id)) {
			if(model.is_owner(dia_id, user_id)) result = "You are already owner of this diamond.";
			else if(model.dibs_insert(user_id, dia_id)==1) {
				result = "Successfully inserted in your dibs.";
			} else {
				result = "Unexpected error, please try again.";
			}
		} else result = "You already have a same thing in your dibs.";
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
