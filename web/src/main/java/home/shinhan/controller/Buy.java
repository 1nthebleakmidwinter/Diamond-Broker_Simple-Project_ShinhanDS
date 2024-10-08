package home.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.shinhan.diabroker.MemDTO;
import home.shinhan.diabroker.Model;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/view/buy.do")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
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
		String owner_id = request.getParameter("owner");
		int price = Integer.parseInt(request.getParameter("price"));
		int dia_id = Integer.parseInt(request.getParameter("dia_id"));
		
		String result;
		
		if(model.is_owner(dia_id, user_id)) {
			result = "You are already owner of this diamond.";
		} else {
			MemDTO owner = model.getById(owner_id);
			
			if(model.getById(user_id).getCredit()<price) {
				result = "Not enough credit.";
			} else if(model.memUpdate_credit(model.getById(user_id).getCredit()-price, user_id)==1) {
				if(model.memUpdate_credit(owner.getCredit()+price*9/10, owner_id)==1) {
					if(model.del_dia(dia_id)==1) {
						result = "Purchased Successfully.";
					} else result = "Purchase Failed !";
				} else result = "Purchase Failed !!";
			} else result = "Purchase Failed !!!";
		}
		
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
