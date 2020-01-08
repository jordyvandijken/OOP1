
public class Actor {
	String name;
	String line;
	
	boolean doneTrade;
	String finishedLine;
	
	Item requiredItem;
	Item tradeitem;
	

	public Actor (String _name, String _line, String _finishedLine) {
		name = _name;
		line = _line;
		
		finishedLine = _finishedLine;
	}
	
	public Item Trade(Item _item) {
		if (requiredItem != null && _item != null && !doneTrade && _item == requiredItem) {
			doneTrade = true;
			return tradeitem;
		}
		
		System.out.println("Are you trying to fool me!");
		
		return null;
	}
	
	public void SetupTrading (Item _required, Item _trade) {
		requiredItem = _required;
		tradeitem = _trade;
	}
	
}
