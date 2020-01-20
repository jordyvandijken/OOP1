import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Inventory {
	public HashMap<String, Item> inv;
	
	private int maxWeight;
	
	public Inventory () {
		inv = new HashMap<String, Item>();
		maxWeight = 100;
	}
	
	public Inventory (int _Max) {
		inv = new HashMap<String, Item>();
		maxWeight = _Max;
	}
	
	public void LookItems() {
		Utils.DisplayText("Item(s): ", 0.05f);
		
		if (inv.isEmpty()) {
			Utils.DisplayText("None", 0.05f);
		} else {
			for(Map.Entry<String, Item> entry : inv.entrySet()) {
				 Utils.DisplayText(entry.getValue().GetName() + " ", 0.05f);
			}
		}
	}
	
	public boolean AddItem(Item _item) {
		int weight = totalWeight();
				
		weight += _item.GetWeight();
		
		if (weight < maxWeight) {
			inv.put(_item.GetName().toLowerCase(), _item);
			return true;
		} else {
			Utils.DisplayText("Too heavy to carry!", 0.05f);
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public int totalWeight() {
		Iterator iterator = inv.entrySet().iterator();
		int tempWeight = 0;
		while(iterator.hasNext()) {
			Map.Entry mapentry = (Entry) iterator.next();
			
			Item value = (Item) mapentry.getValue();
			
			tempWeight += value.GetWeight();
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
