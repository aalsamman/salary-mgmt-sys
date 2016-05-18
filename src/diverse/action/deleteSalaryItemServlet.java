package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.SalaryItemValue;
import diverse.service.SalaryItemService;
import diverse.service.SalaryItemValueService;

public class deleteSalaryItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteSalaryItemServlet() {
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

		doPost(request,response);
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
		
		SalaryItemValue value=new SalaryItemValue();
		SalaryItemValueService s=new SalaryItemValueService();
		List temp=s.getAllItemsValue();
		boolean x=false;
		int i=-1;

		do{
			i++;
			value=(SalaryItemValue) temp.get(i);
			if(value.getSalaryItemID()==Integer.parseInt(id))
				x=true;
		}while(x==false && i<(temp.size()-1));
		
		if(x==false){
			SalaryItemService service = new SalaryItemService();
			service.deleteItem(Integer.parseInt(id));
			request.getRequestDispatcher("/servlet/ManageSalaryItemsServlet").forward(request, response);
		}else{
			request.setAttribute("error", "Error1: This Item can not be deleted, Check the user manual for causes of this error!");
			request.getRequestDispatcher("/servlet/ManageSalaryItemsServlet").forward(request, response);
		}
		
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
