package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.CalculatedSalary;
import diverse.object.FixedSalary;
import diverse.object.SalaryLog;
import diverse.object.TotalSalary;
import diverse.service.SalaryLogService;

public class saveServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public saveServlet() {
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

		List salariesList = (List) request.getSession().getAttribute("sList");
		List calculatedList = (List) request.getSession().getAttribute("cList");
		List totalList = (List) request.getSession().getAttribute("tList");
		int departmentId = (Integer)request.getSession().getAttribute("departmentId");
		
		for(int i=0; i<salariesList.size();i++)
		{
			FixedSalary fx = new FixedSalary();
			fx = (FixedSalary) salariesList.get(i);
			
			SalaryLog sl = new SalaryLog();
			sl.setDepartmentID(departmentId);
			sl.setEmployeeID(fx.getEmployeeId());
			sl.setSalaryItemID(fx.getSalaryItemId());
			sl.setSalaryItemValue(fx.getValue());
			sl.setEditor("michel");
			java.util.Date date= new java.util.Date();
			sl.setEditDate(new Timestamp(date.getTime()));
			
			SalaryLogService logService = new SalaryLogService();
			logService.save(sl);
		}
		
		for(int i=0; i<calculatedList.size();i++)
		{
			CalculatedSalary cs = new CalculatedSalary();
			cs = (CalculatedSalary) calculatedList.get(i);
			
			SalaryLog sl = new SalaryLog();
			sl.setDepartmentID(departmentId);
			sl.setEmployeeID(cs.getEmployeeId());
			sl.setSalaryItemID(cs.getItemId());
			sl.setSalaryItemValue(cs.getValue());
			sl.setEditor("michel");
			java.util.Date date= new java.util.Date();
			sl.setEditDate(new Timestamp(date.getTime()));
			
			SalaryLogService logService = new SalaryLogService();
			logService.save(sl);
		}
		
		for(int i=0; i<totalList.size();i++)
		{
			TotalSalary ts = new TotalSalary();
			ts = (TotalSalary) totalList.get(i);
			
			SalaryLog sl = new SalaryLog();
			sl.setEmployeeID(ts.getEmployeeID());
			sl.setSalaryItemValue(ts.getFxSum()+ts.getIcSum()-ts.getTax());
			java.util.Date date= new java.util.Date();
			long timestamp = date.getTime();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp);
			sl.setMonth(cal.get(Calendar.MONTH)+1);
			
			SalaryLogService logService = new SalaryLogService();
			logService.saveTotal(sl);
		}
		request.getSession().removeAttribute("sList");
		request.getSession().removeAttribute("cList");
		request.getSession().removeAttribute("tList");
		String departmentID=request.getParameter("departmentID");
		String url="/servlet/calculateSalaryServlet?department="+departmentID;
		request.setAttribute("saved", "1");
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
