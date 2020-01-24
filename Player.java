import java.util.ArrayList;
import java.util.List;

public class Player {
	private Room currentRoom;
	private int health;
	private Inventory inv;
	
	List<Room> lastRoom;
	
	public Player(Room startRoom, int startHealth) {
		currentRoom = startRoom;
		setHealth(startHealth);
		
		inv = new Inventory(20);
		
		lastRoom = new ArrayList<Room>();
	}
	
	@SuppressWarnings("unused")
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
	
	@SuppressWarnings("unused")
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
	
	public Room GetCurrentRoom() {
		return currentRoom;
	}
	
	public void GotoRoom(String direction) {
	    // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        Room nextLockedExit = currentRoom.getLockedExit(direction);
        
        if (nextLockedExit != null)
        {
        	if(currentRoom.getLocked(direction) == true))
        	{
        		Utils.DisplayText("This door is locked and can't be unlocked", 0.05f);
        		return;
        	}
        	else
        	{
        		SetLastRoom(currentRoom);
        		currentRoom = nextLockedExit;
        		Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
        		
        		
        	}
        }
        
        
        if (nextRoom == null) {
            Utils.DisplayText("There is no door!", 0.05f);
        }
        else {
            SetLastRoom(currentRoom);
            currentRoom = nextRoom;
            Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
        }
	}


	public int getHealth() {
		return health;
	}
	
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
	
	public void GotoLastRoom() {
		if (lastRoom.size() - 1 > 0) {
			currentRoom = lastRoom.get(lastRoom.size() - 1);
			lastRoom.remove(lastRoom.size() - 1);
            Utils.DisplayText(currentRoom.getLongDescription(), 0.05f);
		} else {
            Utils.DisplayText("Can't Go back any further", 0.05f);
		}
	}


	public void setHealth(int health) {
		this.health = health;
	}

	public Inventory Inventory() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

}
