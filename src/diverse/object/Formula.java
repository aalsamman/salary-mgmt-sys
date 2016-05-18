package diverse.object;

public class Formula {
	
	int itemId;
	int baseItemId;
	double precedent;
	String soperator;
	String name;
	String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBaseItemId() {
		return baseItemId;
	}
	public void setBaseItemId(int baseItemId) {
		this.baseItemId = baseItemId;
	}
	public double getPrecedent() {
		return precedent;
	}
	public void setPrecedent(double precedent) {
		this.precedent = precedent;
	}
	public String getSoperator() {
		return soperator;
	}
	public void setSoperator(String soperator) {
		this.soperator = soperator;
	}
	
	

}
