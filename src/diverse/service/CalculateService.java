package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.dao.CalculateDAO;
import diverse.dao.FixedSalaryDAO;
import diverse.dao.FormulaDAO;
import diverse.dao.ImportedSalaryDAO;
import diverse.object.CalculatedSalary;
import diverse.object.FixedSalary;
import diverse.object.Formula;
import diverse.object.ImportedSalary;
import diverse.util.DBUtil;

public class CalculateService 
{
	public List setCSalaries(int departmentID)
	{
		
		List<FixedSalary> fxList = null;
		List<ImportedSalary> impList = null;
		List<Formula> fList = null;
		List<CalculatedSalary> cList = new ArrayList<CalculatedSalary>();
		FixedSalaryService fxService = new FixedSalaryService();
		fxList = fxService.getFixedSalaries(departmentID);
		/*
		for(int j=0; j<fxList.size();j++)
		{
			FixedSalary fxSalary = new FixedSalary();
			fxSalary = fxList.get(j);
			System.out.println("fxList#########################");
			System.out.println(fxSalary.getEmployeeId()+"\t"+fxSalary.getEmployeeName()+"\t"+fxSalary.getSalaryItemName()+
					"\t"+fxSalary.getValue());
			System.out.println("fxList#########################");
			
		}
		*/
		ImportedSalaryService impService = new ImportedSalaryService();
		impList = impService.getImportedSalaries(departmentID);
		
		FormulaService fService = new FormulaService();
		fList = fService.getFormulas();
		
		for(int i=0; i<fList.size();i++)
		{
			Formula formula = new Formula();
			formula = fList.get(i);
			for(int j=0; j<fxList.size();j++)
			{
				FixedSalary fxSalary = new FixedSalary();
				fxSalary = fxList.get(j);
				if(formula.getBaseItemId()== fxSalary.getSalaryItemId())
				{
					CalculatedSalary cSalary = new CalculatedSalary();
			
					if(formula.getSoperator().equals("*"))
						cSalary.setValue(fxSalary.getValue()*formula.getPrecedent());
					else if (formula.getSoperator().equals("/"))
						cSalary.setValue(fxSalary.getValue()/formula.getPrecedent());
					else if (formula.getSoperator().equals("+"))
						cSalary.setValue(fxSalary.getValue()+formula.getPrecedent());
					else 
						cSalary.setValue(fxSalary.getValue()-formula.getPrecedent());
					
					cSalary.setEmployeeId(fxSalary.getEmployeeId());
					cSalary.setEmployeeName(fxSalary.getEmployeeName());
					cSalary.setItemId(formula.getItemId());
					cSalary.setItemName(formula.getName());
					cSalary.setType(formula.getType());
					
					cList.add(cSalary);
				}
			}
		}
		/*
		for(int i=0; i<cList.size(); i++)
		{
			CalculatedSalary temp = new CalculatedSalary();
			temp = cList.get(i);
			System.out.println("fixedC#######################");
			System.out.println(temp.getEmployeeId()+"\t"+temp.getEmployeeName()+"\t"+temp.getItemName()+"\t"+temp.getType()+temp.getValue());
			System.out.println("fixedC########################");
		}
		*/
		for(int i=0; i<fList.size();i++)
		{
			Formula formula = new Formula();
			formula = fList.get(i);
			for(int j=0; j<impList.size();j++)
			{
				ImportedSalary impSalary = new ImportedSalary();
				impSalary = impList.get(j);
				if(formula.getBaseItemId()== impSalary.getSalaryItemId())
				{
					CalculatedSalary cSalary = new CalculatedSalary();
			
					if(formula.getSoperator().equals("*"))
						cSalary.setValue(impSalary.getValue()*formula.getPrecedent());
					else if (formula.getSoperator().equals("/"))
						cSalary.setValue(impSalary.getValue()/formula.getPrecedent());
					else if (formula.getSoperator().equals("+"))
						cSalary.setValue(impSalary.getValue()+formula.getPrecedent());
					else 
						cSalary.setValue(impSalary.getValue()-formula.getPrecedent());
					
					cSalary.setEmployeeId(impSalary.getEmployeeId());
					cSalary.setEmployeeName(impSalary.getEmployeeName());
					cSalary.setItemId(formula.getItemId());
					cSalary.setItemName(formula.getName());
					cSalary.setType(formula.getType());
					
					cList.add(cSalary);
				}
			}
		}
		/*
		for(int i=0; i<cList.size(); i++)
		{
			CalculatedSalary temp = new CalculatedSalary();
			temp = cList.get(i);
			System.out.println("#######################");
			System.out.println(temp.getEmployeeId()+"\t"+temp.getEmployeeName()+"\t"+temp.getItemName()+"\t"+temp.getType()+temp.getValue());
			System.out.println("########################");
		}
		*/
		return cList;
	}
	
	public List getAllSalaries(int departmentID)
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		CalculateDAO dao=new CalculateDAO(connect);
		List<FixedSalary> tempList=null;

		
		try {
			tempList=dao.getAllSalaries(departmentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return tempList;
	}

}
