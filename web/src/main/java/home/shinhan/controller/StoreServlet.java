package home.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.shinhan.diabroker.DiaDTO;
import home.shinhan.diabroker.Model;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/view/store.do")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		int sel = Integer.parseInt(request.getParameter("sel"));
		int total = model.totalDia();
		int page = total%7==0?total/7:total/7+1;
		int cur = page==0 ? 0 : sel/7+1;
		List<DiaDTO> dialist = model.table_set(sel, total);
		request.setAttribute("dialist", dialist);
		request.setAttribute("page", page);		
		request.setAttribute("cur", cur);		
		
		RequestDispatcher rd = request.getRequestDispatcher("../ajax_jsp/dias.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
