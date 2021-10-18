package pbeave.wgu.ims3.model;

/**
 * @author Patricia Beavers
 * Model for outsourced part
 */
public class OutsourcePart extends Part {
	/**
	 * company name for outsourced part
	 */
	private String companyName;
	/**
	 * constructor for outsourced part
	 * @param id for outsourced part
	 * @param name for outsourced part
	 * @param price for outsourced part
	 * @param stock for outsourced part
	 * @param min for outsourced part
	 * @param max for outsourced part
	 * @param companyName for outsourced part
	 */
	public OutsourcePart(int id, String name, double price, int stock, int min, int max, String companyName) {
		super(id, name, price, stock, min, max);
		setCompanyName(companyName);
	}
	/**
	 * Getter for company name
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * setter for company name
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	
}
