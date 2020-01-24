/**
 *  This is the Item class
 *  It contains the Item name, weight and description
 * 
 * @author  Jordy v Dijken
 * @version 2020.1.22
 */
public class Item {
	private String name;
	private int weight;
	private String discription;
	
	/**
     * Instantiate Item with the name, weight and description
     * @param String the item name
     * @param int The item weight.
     * @param String the item description.
     */
	public Item(String _Name, int _Weight, String _Discription) {
		name = _Name;
		weight = _Weight;
		discription = _Discription;
	}
	
	/**
     * Get the Item name
     * @return String Item name
     */
	public String GetName() {
		return this.name;
	}
	
	/**
     * Get the Item weight
     * @return int Item weight.
     */
	public int GetWeight() {
		return this.weight;
	}
	
	/**
     * Get the Item description
     * @return String Item description.
     */
	public String GetDiscription() {
		return this.discription;
	}
}
