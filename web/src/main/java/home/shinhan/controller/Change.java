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
 * Servlet implementation class Change
 */
@WebServlet("/view/change.do")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
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
		String target = request.getParameter("target");
		String val = request.getParameter("val");
		
		if(target.equals("name")) {
			if(model.memUpdate_name(val, user_id)==1) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(0);
			}
		} else if(target.equals("pn")) {
			if(model.pn_dup_chk(val)) {
				if(model.memUpdate_pn(val, user_id)==1) {
					response.getWriter().print(1);
                } else {
                	response.getWriter().print(0);
                }
			} else {
				response.getWriter().print(2);
			}
		} else if(target.equals("email")) {
			if(model.email_dup_chk(val)) {
				if(model.memUpdate_email(val, user_id)==1) {
					response.getWriter().print(1);
                } else {
                	response.getWriter().print(0);
                }
			} else {
				response.getWriter().print(2);
			}
		} else if(target.equals("card")) {
			if(model.card_dup_chk(val)) {
				if(model.memUpdate_card(val, user_id)==1) {
					response.getWriter().print(1);
                } else {
                	response.getWriter().print(0);
                }
			} else {
				response.getWriter().print(2);
			}
		} else {
			if(model.getById(user_id).getCard_id()==null) {
				response.getWriter().print(3);
			} else {
				if(model.memUpdate_credit(model.getById(user_id).getCredit()+Integer.parseInt(val), user_id)==1) {
					response.getWriter().print(1);
				} else {
					response.getWriter().print(0);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
