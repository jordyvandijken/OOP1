
public class Item {
	private String name;
	private int weight;
	private String discription;
	
	public Item(String _Name, int _Weight, String _Discription) {
		name = _Name;
		weight = _Weight;
		discription = _Discription;
	}
	
	public String GetName() {
		return this.name;
	}
	
	public int GetWeight() {
		return this.weight;
	}
	
	public String GetDiscription() {
		return this.discription;
	}
}
