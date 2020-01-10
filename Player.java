
public class Player {
	private Room currentRoom;
	private int health;
	private Inventory inv;
	
	public Player(Room startRoom, int startHealth) {
		currentRoom = startRoom;
		setHealth(startHealth);
		
		inv = new Inventory(20);
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
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
	}


	public int getHealth() {
		return health;
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
