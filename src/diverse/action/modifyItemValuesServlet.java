package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.Department;
import diverse.object.Employee;
import diverse.object.SalaryItem;
import diverse.object.SalaryItemValue;
import diverse.service.DepartmentService;
import diverse.service.EmployeeService;
import diverse.service.SalaryItemService;
import diverse.service.SalaryItemValueService;

public class modifyItemValuesServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public modifyItemValuesServlet() {
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
		
		
		String departmentID=request.getParameter("departmentID2");	
		String url=null;
		String itemID=request.getParameter("salaryItemID2");
		String newvalue=request.getParameter("value2");
		Employee employee=new Employee();
		SalaryItemValue value=new SalaryItemValue();
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		
		EmployeeService eservice=new EmployeeService();
		List<Employee> elist=new ArrayList();
		elist=eservice.getEmployeesByDepartment(Integer.parseInt(departmentID));
		
		SalaryItemValueService iservice=new SalaryItemValueService();
		List ilist=new ArrayList();
		ilist=iservice.getAllItemsValue();
		
		for(int i=0;i<elist.size();i++){
			employee=elist.get(i);
			for(int j=0;j<ilist.size();j++){
				value=(SalaryItemValue) ilist.get(j);
				if(employee.getId()==value.getEmployeeID() && value.getSalaryItemID()==Integer.parseInt(itemID)){
					if(!newvalue.isEmpty())
						value.setValue(Double.parseDouble(newvalue));
					else value.setValue(0);
					value.setSalaryItemID(Integer.parseInt(itemID));
					value.setEmployeeID(employee.getId());
					value.setDate(timestamp);
					iservice.modifyValue(value);
					break;
				}
			}
			if(employee.getId()!=value.getEmployeeID()){
				if(!newvalue.isEmpty())
					value.setValue(Double.parseDouble(newvalue));
				else value.setValue(0);
				value.setSalaryItemID(Integer.parseInt(itemID));
				value.setEmployeeID(employee.getId());
				value.setDate(timestamp);
				iservice.insertValue(value);
			}else if(employee.getId()==value.getEmployeeID() && value.getSalaryItemID()==Integer.parseInt(itemID)){
				if(!newvalue.isEmpty())
					value.setValue(Double.parseDouble(newvalue));
				else value.setValue(0);
				value.setSalaryItemID(Integer.parseInt(itemID));
				value.setEmployeeID(employee.getId());
				value.setDate(timestamp);
				iservice.modifyValue(value);				
			}
		}
		SalaryItem d=new SalaryItem();
		SalaryItemService ds=new SalaryItemService();
		d=ds.getItem(Integer.parseInt(itemID));
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
