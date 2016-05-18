package diverse.object;

public class SalaryItem {
	
	private int id;
	private int baseId;
	private float precedent;
	private boolean displayed;
	private String name;
	private String category;
	private String type;
	private String operator;
	private String comment;
	
	public int getBaseId() 
	{
		return baseId;
	}
	public void setBaseId(int baseId) 
	{
		this.baseId = baseId;
	}
	public float getPrecedent()
	{
		return precedent;
	}
	public void setPrecedent(float precedent) 
	{
		this.precedent = precedent;
	}
	public String getOperator() 
	{
		return operator;
	}
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCategory() 
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public boolean isDisplayed() 
	{
		return displayed;
	}
	public void setDisplayed(boolean displayed) 
	{
		this.displayed = displayed;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}

}
