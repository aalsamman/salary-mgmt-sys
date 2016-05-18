package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.service.SalaryLogService;

public class payServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public payServlet() {
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

		doPost(request, response);
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

		int departmentId = (Integer)request.getSession().getAttribute("departmentId");
		//String dep=(String) request.getSession().getAttribute("departmentId");
		String dep=""+departmentId;
		
		java.util.Date date= new java.util.Date();
		long timestamp = date.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int month = cal.get(Calendar.MONTH)+1;
		
		SalaryLogService logService = new SalaryLogService();
		logService.pay(departmentId,month);
		
		request.getSession().removeAttribute("departmentId");
		String url="/servlet/calculateSalaryServlet?department="+dep;
		request.getRequestDispatcher(url).forward(request,	response);
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
