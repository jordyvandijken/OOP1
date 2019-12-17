
public class Item {
	private String name;
	private int weigth;
	private String discription;
	
	public Item(String _Name, int _Weight, String _Discription) {
		name = _Name;
		weigth = _Weight;
		discription = _Discription;
	}
	
	public String GetName() {
		return this.name;
	}
	
	public int GetWeigth() {
		return this.weigth;
	}
	
	public String GetDiscription() {
		return this.discription;
	}
}
