
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
	
	public Room GetCurrentRoom() {
		return currentRoom;
	}
	
	public void GotoRoom(String direction) {
        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null) {
                System.out.print("north ");
            }
            if(currentRoom.eastExit != null) {
                System.out.print("east ");
            }
            if(currentRoom.southExit != null) {
                System.out.print("south ");
            }
            if(currentRoom.westExit != null) {
                System.out.print("west ");
            }
            System.out.println();
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
