package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.SalaryItem;
import diverse.service.SalaryItemService;

public class modifySalaryItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public modifySalaryItemServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String displayed = request.getParameter("displayed");
		String comment = request.getParameter("comment");
		String baseId = request.getParameter("baseItemId");
		String operator = request.getParameter("operator");
		String precedent = request.getParameter("precedent");
		
		
		SalaryItem item = new SalaryItem();
		item.setId(Integer.parseInt(id));
		item.setBaseId(Integer.parseInt(baseId));
		item.setOperator(operator);
		if (precedent==null)
			item.setPrecedent(0);
		else
			item.setPrecedent(Float.parseFloat(precedent));
		item.setCategory(category);
		item.setName(name);
		item.setType(type);
		if(displayed==null)
			item.setDisplayed(false);
		else
			item.setDisplayed(true);
		item.setComment(comment);
		
		
		SalaryItemService service= new SalaryItemService(); 
		service.modifyItem(item);
		request.getRequestDispatcher("/servlet/ManageSalaryItemsServlet").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
