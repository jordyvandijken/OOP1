import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Inventory {
	public HashMap<String, Item> inv;
	
	private int maxWeigth;
	
	public Inventory () {
		inv = new HashMap<String, Item>();
		maxWeigth = 100;
	}
	
	public Inventory (int _Max) {
		inv = new HashMap<String, Item>();
		maxWeigth = _Max;
	}
	
	public void GetItems() {
		 System.out.println("Item(s): ");
		for(Map.Entry<String, Item> entry : inv.entrySet()) {
			 System.out.println(entry.getValue().GetName() + " ");
		}
	}
	
	public void AddItem(Item _item) {
		int weigth = totalWeight();
				
		weigth += _item.GetWeigth();
		
		if (weigth < maxWeigth) {
			inv.put(_item.GetName().toLowerCase(), _item);	
		} else {
			System.out.println("Too heavy to carry!");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public int totalWeight() {
		Iterator iterator = inv.entrySet().iterator();
		int tempWeight = 0;
		while(iterator.hasNext()) {
			Map.Entry mapentry = (Entry) iterator.next();
			
			Item value = (Item) mapentry.getValue();
			
			tempWeight += value.GetWeigth();
		}
		return tempWeight;
	}
	
	public void RemoveItem(Item item) {
		inv.remove(item.GetName().toLowerCase());
	}
	
	public Item GetItem(String str) {
		return inv.get(str);
	}
	
	public Item TakeItem(String str) {
		return inv.remove(str);
	}
}
