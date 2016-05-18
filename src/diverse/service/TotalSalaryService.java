package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.dao.FixedSalaryDAO;
import diverse.dao.FormulaDAO;
import diverse.dao.ImportedSalaryDAO;
import diverse.object.CalculatedSalary;
import diverse.object.FixedSalary;
import diverse.object.Formula;
import diverse.object.ImportedSalary;
import diverse.object.TotalSalary;
import diverse.util.DBUtil;

public class TotalSalaryService 
{
	public List setTotalValues(int departmentID)
	{
		List<TotalSalary> tList = new ArrayList<TotalSalary>();
		List<FixedSalary> fxList = null;
		List<CalculatedSalary> cList = null;
		
		FixedSalaryService fxService = new FixedSalaryService();
		fxList = fxService.getFixedSalaries(departmentID);
		
		CalculateService cService = new CalculateService();
		cList = cService.setCSalaries(departmentID);
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
		for(int i=0; i<fxList.size(); i++)
		{
			FixedSalary fxItem = new FixedSalary();
			TotalSalary tSalary = new TotalSalary();
			fxItem = fxList.get(i);
			double fxSum = 0;
			for(int j=0; j<fxList.size();j++)
			{
				FixedSalary temp = new FixedSalary();
				temp = fxList.get(j);
				if(fxItem.getEmployeeId()==temp.getEmployeeId())
				{
					fxSum += temp.getValue();
				}
			}
			tSalary.setEmployeeID(fxItem.getEmployeeId());
			tSalary.setFxSum(fxSum);
			tSalary.setName(fxItem.getEmployeeName());
			if(tList.isEmpty()||!tList.contains(tSalary))
				tList.add(tSalary);
				
		}
		
		for(int i=0; i<cList.size();i++)
		{
			CalculatedSalary cItem = new CalculatedSalary();
			TotalSalary tSalary = new TotalSalary();
			cItem = cList.get(i);
			double icSum = 0;
			for(int j=0; j<cList.size();j++)
			{
				CalculatedSalary temp = new CalculatedSalary();
				temp = cList.get(j);
				if(cItem.getEmployeeId()==temp.getEmployeeId())
				{
					if(temp.getType().equals("Y"))
						icSum += temp.getValue();
					else if(temp.getType().equals("N"))
						icSum-= temp.getValue();
					
				}
			}
			
			tSalary.setEmployeeID(cItem.getEmployeeId());
			tSalary.setIcSum(icSum);
			tSalary.setName(cItem.getEmployeeName());
			
			for(int k=0; k<tList.size();k++)
			{
				TotalSalary tempSalary1 = new TotalSalary();
				tempSalary1 = tList.get(k);
				
				if(tempSalary1.getEmployeeID()==tSalary.getEmployeeID())
				{
					tempSalary1.setIcSum(tSalary.getIcSum());
					tList.set(k, tempSalary1);
				}
				
			}
			
		}
		for(int i=0; i<tList.size();i++)
		{
			TotalSalary tSalary = new TotalSalary();
			tSalary = tList.get(i);
			double taxMoney = 0, tax = 0;
			for(int j=0; j<fxList.size();j++)
			{
				FixedSalary fxSalary = new FixedSalary();
				fxSalary = fxList.get(j);
				if(fxSalary.getEmployeeId()== tSalary.getEmployeeID()&& fxSalary.getSalaryItemName().equals("Basic Salary"))
					taxMoney = fxSalary.getValue();
			}
			for(int k=0; k<cList.size();k++)
			{
				CalculatedSalary cSalary = new CalculatedSalary();
				cSalary = cList.get(k);
				if(cSalary.getEmployeeId()== tSalary.getEmployeeID() && cSalary.getItemName().equalsIgnoreCase("Pension Insurance(I)"))
					taxMoney -= cSalary.getValue();
				else if(cSalary.getEmployeeId()== tSalary.getEmployeeID() && cSalary.getItemName().equalsIgnoreCase("Medical Insurance(I)"))
					taxMoney -= cSalary.getValue();
				else if(cSalary.getEmployeeId()== tSalary.getEmployeeID() && cSalary.getItemName().equalsIgnoreCase("Unemployment Insurance(I)"))
					taxMoney -= cSalary.getValue();
				else if(cSalary.getEmployeeId()== tSalary.getEmployeeID() && cSalary.getItemName().equalsIgnoreCase("Occupational Injury(C)"))
					taxMoney -= cSalary.getValue();
				else if(cSalary.getEmployeeId()== tSalary.getEmployeeID() && cSalary.getItemName().equalsIgnoreCase("Housing Provident Funds(I)"))
					taxMoney -= cSalary.getValue();
			}
			
			if(taxMoney<=1500)
				tax = taxMoney*0.03;
			else if(taxMoney>1500 && taxMoney<=4500)
				tax = taxMoney*0.1 - 105;
			else if(taxMoney>4500 && taxMoney<=9000)
				tax = taxMoney*0.2 - 555;
			else if(taxMoney>9000 && taxMoney<=35000)
				tax = taxMoney*0.25 - 1005;
			else if(taxMoney>35000 && taxMoney<=55000)
				tax = taxMoney*0.3 - 2775;
			else if(taxMoney>55000 && taxMoney<=80000)
				tax = taxMoney*0.35 - 5505;
			else 
				tax = taxMoney*0.45 - 13505;
			
			tSalary.setTax(tax);
			
		}
		/*
		for(int i=0; i<tList.size(); i++)
		{
			TotalSalary temp = new TotalSalary();
			temp = tList.get(i);
			System.out.println("#######################");
			System.out.println(temp.getEmployeeID()+"\t"+temp.getName()+"\t"+temp.getFxSum()+"\t"+temp.getIcSum()+
			"\t"+temp.getTax());
			System.out.println("########################");
		}
		*/
		
		return tList;
	}
}
