package pbeave.wgu.ims3.model;

/**
 * @author Patricia Beavers
 * model for Inhouse part
 */
public class InHousePart extends Part {
	/**
	 * machine ID for in house part
	 */
	private int machineID;
	/**
	 * Constructor for inhouse part
	 * @param id for inhouse part 
	 * @param name for inhouse part
	 * @param price for inhouse part
	 * @param stock for inhouse part
	 * @param min for inhouse part
	 * @param max for inhouse part
	 * @param machineID for inhouse part
	 */
	public InHousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
		super(id, name, price, stock, min, max);
		this.machineID = machineID;
	}
	/**
	 * Getter for machine ID
	 * @return machine ID
	 */
	public int getMachineID() {
		return machineID;
	}
	/**
	 * Setter for machine ID
	 * @param machineID
	 */
	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}

}
