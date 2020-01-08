
public class Actor {
	String name;
	String line;
	
	boolean hasTrade;
	boolean doneTrade;
	String finishedLine;
	
	Item tradeitem;
	
	public Actor (String _name, String _line, boolean _hasTrade, String _finishedLine, Item _tradeItem) {
		name = _name;
		line = _line;
		
		hasTrade = _hasTrade;
		finishedLine = _finishedLine;
		
		tradeitem = _tradeItem;	
	}
	
	
}
