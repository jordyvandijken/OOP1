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
			System.out.println("You grabed: " + item.GetName());

		} else {
			// the room does not contain this item
			System.out.println("There is no such item here!");
		}
	}
	
	@SuppressWarnings("unused")
	public void DropItem (String str) {
		Item item = this.Inventory().GetItem(str.toLowerCase());

		if (item != null) {
			currentRoom.Inventory().AddItem(this.Inventory().TakeItem(str.toLowerCase()));
			System.out.println("You grabed: " + item.GetName());

		} else {
			// the room does not contain this item
			System.out.println("There is no such item here!");
		}
	}
	
	public Room GetCurrentRoom() {
		return currentRoom;
	}
	
	public void GotoRoom(String direction) {
	    // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            SetLastRoom(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
            System.out.println(currentRoom.getLongDescription());
		} else {
            System.out.println("Can't Go back any further");
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
