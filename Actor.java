/**
 *  This is the Actor class
 *  It contains the name,  lines and trade (if set) of the actor
 * 
 * @author  Jordy v Dijken
 * @version 2020.1.22
 */
public class Actor {
	String name; // Name of the actor
	String line; // Line the actor say when talking 
	
	boolean doneTrade; // Checks if the trade is done
	String finishedLine; // The line the actor will say when trading
	
	Item requiredItem; // the required item for trading
	Item tradeitem; // the item the player will receive when trading
	
	/**
     * Instantiate the Actor with the name and lines.
     * @param String the actor name.
     * @param String the line of the actor when talking.
     * @param String the line of the actor when trading.
     */
	public Actor (String _name, String _line, String _finishedLine) {
		name = _name;
		line = _line;
		finishedLine = _finishedLine;
	}
	
	/**
     * Trade with the actor
     * @param Item for the required trade
     * @return Item if trade was done with the correct Item
     * @see Item
     */
	public Item Trade(Item _item) {
		if (requiredItem != null && _item != null && !doneTrade && _item == requiredItem) {
			doneTrade = true;
			return tradeitem;
		}
		
		Utils.DisplayText("Are you trying to fool me!", 0.05f);

		return null;
	}
	
	/**
     * Set up trading for the Actor, it requires an item for the trade and an item the player receives when the trade is done
     * @param Item the item the player needs to trade.
     * @param Item that will be returned if traded correctly.
     * @return void.
     * @see Item
     */
	public void SetupTrading (Item _required, Item _trade) {
		requiredItem = _required;
		tradeitem = _trade;
	}
	
}
