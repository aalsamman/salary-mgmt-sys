package diverse.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.object.SalaryLog;

public class exportServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public exportServlet() {
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

		List logList = (List) request.getSession().getAttribute("logList");
		int month = (Integer) request.getSession().getAttribute("month");
		int departmentId = (Integer) request.getSession().getAttribute("departmentId");
		String dep=""+departmentId;
		String m=""+month;
		String url="/servlet/queryLogServlet?department="+dep+"&month="+m;
		
		try
		{
	
			//PrintWriter out = response.getWriter();
			File filename = new File("c:\\csv\\"+"export_csv_dep_"+departmentId+"_month_"+month+".csv");
			FileWriter fw = new FileWriter(filename);
		  
		  
			fw.append("Employee ID");
			fw.append(',');
			fw.append("Employee Name");
		  
			int index = ((SalaryLog)logList.get(0)).getEmployeeID();
			String header = "";
			for (int i = 0; i<logList.size(); i++)
			{
				SalaryLog sItem =(SalaryLog)logList.get(i);
					
				if(sItem.getEmployeeID()== index)
					header += ","+sItem.getItemName();
		  
		  }
		 
		  fw.append(header);
		  fw.append('\n');
		  
		  List<Integer> id = new ArrayList<Integer>();
		  for (int i=0; i<logList.size();i++)
		  {
			  SalaryLog sItem =(SalaryLog)logList.get(i); 
			  if(id.isEmpty()||!id.contains(sItem.getEmployeeID()))
			  {
				  id.add(sItem.getEmployeeID());
				  fw.append(""+sItem.getEmployeeID());
				  fw.append(',');
				  fw.append(sItem.getName());
				  String entry = "";
				  for (int j=0; j<logList.size();j++)
				  {
					  SalaryLog temp =(SalaryLog)logList.get(j); 
					  if(temp.getEmployeeID()== sItem.getEmployeeID())
					  {
						  if(temp.getSalaryItemValue()!=0)
							  entry +=","+temp.getSalaryItemValue();
						  else
							  entry +=", ";
					  }
				  }
				  fw.append(entry);
				  fw.append('\n');
			  }
		  }
		  fw.flush();
		  fw.close();
		} 
		catch (Exception ex) 
		{
				ex.printStackTrace ();
		}
			
			request.getSession().removeAttribute("logList");
			request.getSession().removeAttribute("deppartmentId");
			request.getSession().removeAttribute("month");
			
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
