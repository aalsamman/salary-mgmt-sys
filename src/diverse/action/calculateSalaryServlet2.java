package diverse.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diverse.service.CalculateService;
import diverse.service.TotalSalaryService;

public class calculateSalaryServlet2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public calculateSalaryServlet2() {
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

		CalculateService cService = new CalculateService();
		List salariesList = cService.getAllSalaries(Integer.parseInt(request
				.getParameter("department")));
		List cList = cService.setCSalaries(Integer.parseInt(request
				.getParameter("department")));
		// ImportedSalaryService impService = new ImportedSalaryService();
		// List impList =
		// impService.getImportedSalaries(Integer.parseInt(request.getParameter("department")));

		TotalSalaryService tService = new TotalSalaryService();
		List tList = tService.setTotalValues(Integer.parseInt(request
				.getParameter("department")));

		request.getSession().setAttribute("sList", salariesList);
		request.getSession().setAttribute("cList", cList);
		request.getSession().setAttribute("tList", tList);
		request.getSession().setAttribute("departmentId",
				Integer.parseInt(request.getParameter("department")));
		// request.getSession().setAttribute("sList", salariesList);
		/*
		 * System.out.println("salariesList#########################"); for(int
		 * j=0; j<salariesList.size();j++) { FixedSalary fxSalary = new
		 * FixedSalary(); fxSalary = (FixedSalary) salariesList.get(j);
		 * 
		 * 
		 * System.out.println(fxSalary.getEmployeeId()+"\t"+fxSalary.getEmployeeName
		 * ()+"\t"+fxSalary.getSalaryItemName()+ "\t"+fxSalary.getValue()); }
		 * 
		 * System.out.println("Calculated#######################"); for(int i=0;
		 * i<cList.size(); i++) { CalculatedSalary temp = new
		 * CalculatedSalary(); temp = (CalculatedSalary) cList.get(i);
		 * 
		 * 
		 * System.out.println(temp.getEmployeeId()+"\t"+temp.getEmployeeName()+"\t"
		 * +temp.getItemName()+"\t"+temp.getType()+temp.getValue());
		 * 
		 * } System.out.println("Total########################"); for(int i=0;
		 * i<tList.size(); i++) { TotalSalary temp = new TotalSalary(); temp =
		 * (TotalSalary)tList.get(i);
		 * System.out.println(temp.getEmployeeID()+"\t"
		 * +temp.getName()+"\t"+temp.getFxSum()+"\t"+temp.getIcSum()); }
		 * System.out.println("Calculated#######################");
		 */
		request.setAttribute("departmentID", request.getParameter("department"));
		
		String saved = (String)request.getAttribute("saved");
		
		if(saved == null || saved.equals(""))
		{
			request.setAttribute("saved", "0");
		}
		
		/*if(request.getParameterMap().containsKey("saved")){
			String s=(String)request.getAttribute("saved");
			if(!s.equals("1")){
				request.setAttribute("saved", "0");
			}		
		}else{request.setAttribute("saved", "0");} 		*/
		request.getRequestDispatcher("/calculate.jsp").forward(request,	response);
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
