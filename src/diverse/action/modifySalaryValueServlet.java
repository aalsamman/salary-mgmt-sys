package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.Department;
import diverse.object.SalaryItem;
import diverse.object.SalaryItemValue;
import diverse.service.DepartmentService;
import diverse.service.SalaryItemService;
import diverse.service.SalaryItemValueService;

public class modifySalaryValueServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public modifySalaryValueServlet() {
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

		String employeeID=request.getParameter("employeeID");
		String salaryItemID=request.getParameter("salaryItemID");
		String value=request.getParameter("value");
		String editor=request.getParameter("editor");
		String departmentID=request.getParameter("departmentID");
		String url=null;
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());//here we save the date of editing the value and pass it to the edit fun.
		
		SalaryItemValue svalue=new SalaryItemValue();
		svalue.setDate(timestamp);
		svalue.setEditor(editor);
		svalue.setEmployeeID(Integer.parseInt(employeeID));
		svalue.setSalaryItemID(Integer.parseInt(salaryItemID));
		if(!value.isEmpty())
			svalue.setValue(Double.parseDouble(value));
		else svalue.setValue(0);
		
		SalaryItemValueService service=new SalaryItemValueService();
		if(service.getValue(svalue.getEmployeeID(), svalue.getSalaryItemID())!=null){
			service.modifyValue(svalue);
		}else{
			service.insertValue(svalue);
		}
		
		SalaryItem d=new SalaryItem();
		SalaryItemService ds=new SalaryItemService();
		d=ds.getItem(Integer.parseInt(salaryItemID));
		if(d.getCategory().equals("fixed"))
			url="/DiverseSalaryManager/servlet/queryFixedSalaryItemsServlet?department="+departmentID;
		else url="/DiverseSalaryManager/servlet/queryImportedSalaryItemsServlet?department="+departmentID;
		response.sendRedirect(url);
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
