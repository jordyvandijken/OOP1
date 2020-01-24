/**
 *  This is the player class
 *  It contains the room the player is in,
 *  the current health and its inventory.
 * 
 * @author  Jordy v Dijken
 * @version 2020.1.22
 */

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Room currentRoom;
	private int health;
	private Inventory inv;
	
	List<Room> lastRoom;
	
	/**
     * The player is given a start point (Room) and a amount of health
     * @param startRoom The room to start in.
     * @param startHealth the amount of health the player has.
     */
	public Player(Room startRoom, int startHealth) {
		currentRoom = startRoom;
		SetHealth(startHealth);
		
		inv = new Inventory(20);
		
		lastRoom = new ArrayList<Room>();
	}
	
	/**
     * Takes an item from the current room if it exists and adds it to the inventory.
     * @param str for the item name.
     */
	public void PickUpItem (String str) {
		Item item = currentRoom.Inventory().GetItem(str.toLowerCase());

		if (item != null) {
			inv.AddItem(currentRoom.Inventory().TakeItem(str.toLowerCase()));
			Utils.DisplayText("You grabbed: " + item.GetName(), 0.05f);

		} else {
			// the room does not contain this item
			Utils.DisplayText("There is no such item here!", 0.05f);
		}
	}
	
	/**
     * Drops item from inventory to current room if item exists
     * @param str item name.
     */
	public void DropItem (String str) {
		Item item = this.Inventory().GetItem(str.toLowerCase());

		if (item != null) {
			currentRoom.Inventory().AddItem(this.Inventory().TakeItem(str.toLowerCase()));
			Utils.DisplayText("You grabbed: " + item.GetName(), 0.05f);

		} else {
			// the room does not contain this item
			Utils.DisplayText("There is no such item here!", 0.05f);
		}
	}
	
	/**
     * Return the current room of the player.
     * @return Room .
     */
	public Room GetCurrentRoom() {
		return currentRoom;
	}
	
	/**
     * Tries to go to room with the given direction.
     * @param direction the direction of the room
     */
	public void GotoRoom(String direction) {
	    // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        Room nextLockedExit = currentRoom.getLockedExit(direction);
        
        
        if (nextLockedExit != null)
        {
        	Item requiredKey = currentRoom.getActualExit(direction).getRequiredKey();
        	if(currentRoom.getLocked(direction) == true)
        	{
        		Utils.DisplayText("This door is locked", 0.05f);
        		if(requiredKey == null) {
        			Utils.DisplayText(" and can't be unlocked", 0.05f);
        		}
        		else {
        			Utils.DisplayText("you need the " + requiredKey.GetName() + " to unlock it.", 0.05f);
        		}
        		
        	}
        	else
        	{
        		SetLastRoom(currentRoom);
        		currentRoom = nextLockedExit;
        		Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
        		
        		
        	}
        }
        
        
        else if (nextRoom == null) {
            Utils.DisplayText("There is no door!", 0.05f);
        }
        else {
            SetLastRoom(currentRoom);
            currentRoom = nextRoom;
            Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
        }
	}

	/**
     * Get the player current health
     * @return int amount of health.
     */
	public int GetHealth() {
		return health;
	}
	
	/**
     * Set the last room the player was in
     * @param newRoom that needs to be added to the last room list.
     */
	private void SetLastRoom(Room newRoom) {
		if (currentRoom.ContainsRoom(newRoom)) {
			if (lastRoom.get(lastRoom.size() - 2) != newRoom) {
				lastRoom.add(newRoom);
			}
		} else {
			//lastRoom = new ArrayList<Room>();
			lastRoom.add(newRoom);
		}
	}
	
	/**
     * The player returns to the last room it was in
     */
	public void GotoLastRoom() {
		if (lastRoom.size() - 1 > 0) {
			currentRoom = lastRoom.get(lastRoom.size() - 1);
			lastRoom.remove(lastRoom.size() - 1);
            Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
		} else {
            Utils.DisplayText("Can't Go back any further", 0.05f);
		}
	}

	/**
     * Set the players health.
     * @param SetHealth An amount of health to set.
     */
	public void SetHealth(int SetHealth) {
		this.health = SetHealth;
	}

	/**
     * Get the inventory of the player
     * @return Inventory of the player
     */
	public Inventory Inventory() {
		return inv;
	}

	/**
     * Set inventory of the player
     * @param inv The inventory to set.
     */
	public void SetInv(Inventory inv) {
		this.inv = inv;
	}

}
