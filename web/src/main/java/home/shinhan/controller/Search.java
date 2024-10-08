package home.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.shinhan.diabroker.DiaDTO;
import home.shinhan.diabroker.Model;

/**
 * Servlet implementation class Search
 */
@WebServlet("/view/search.do")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = new Model();
		
		String price = request.getParameter("price").contains("Option") ? "" : request.getParameter("price");
		String carat = request.getParameter("carat").contains("Option") ? "" : request.getParameter("carat");
		String cut = request.getParameter("cut").contains("Option") ? "" : request.getParameter("cut");
		String color = request.getParameter("color").contains("Option") ? "" : request.getParameter("color");
		String clarity = request.getParameter("clarity").contains("Option") ? "" : request.getParameter("clarity");
		String depth = request.getParameter("depth").contains("Option") ? "" : request.getParameter("depth");
		String table = request.getParameter("table").contains("Option") ? "" : request.getParameter("table");
		
		String sql = model.makeSql(price, carat, cut, color, clarity, depth, table);
		int sel = Integer.parseInt(request.getParameter("sel"));
		int total = model.totalDia(sql);
		int page = total%7==0?total/7:total/7+1;
		int cur = page==0 ? 0 : sel/7+1;
		
		List<DiaDTO> dialist = model.table_set(sel, total, sql);
		request.setAttribute("dialist", dialist);
		request.setAttribute("page", page);		
		request.setAttribute("cur", cur);	
		
		request.getRequestDispatcher("../ajax_jsp/dias.jsp").forward(request, response);
	}

}
