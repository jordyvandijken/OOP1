import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    private HashMap <String, Room> exits;

    private Inventory inv;

    private HashMap<String, Actor> actors;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        inv = new Inventory();
        exits = new HashMap<String, Room>();
        actors = new HashMap<String, Actor>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param The direction
     * @param The room in that direction
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }
    
    public String getLongDescription()
    {
        return "You are" + description + ".\n" + getExitString();
    }
    
    private String getExitString()
    {
        String returnString = "Exits: ";
        Set <String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
	public Inventory Inventory() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	
	public void LookRoom() {
		Inventory().LookItems();
		
		LookActors();
	}
	
	public boolean ContainsRoom(Room exit) {
		if (exits.containsValue(exit)) {
			return true;
		}
		
		return false;
	}

	public void AddActor (Actor _actor) {
		actors.put(_actor.name.toLowerCase(), _actor);
	}
	
	public void Interact(String _name) {
		if (actors.containsKey(_name.toLowerCase())) {
			Utils.DisplayText(actors.get(_name.toLowerCase()).line, 0.05f);
		} else {
			Utils.DisplayText("There is no such person here!", 0.05f);
		}
	}
	
	public Actor GetActor(String _name) {
		if (actors.containsKey(_name.toLowerCase())) {
			return actors.get(_name.toLowerCase());
		} 
		return null;
	}
	
	public void LookActors() {
		Utils.DisplayText("Actors: ", 0.05f);
		
		if (actors.isEmpty()) {
			Utils.DisplayText("None", 0.05f);
		
		} else {
			for(Map.Entry<String, Actor> entry : actors.entrySet()) {
				 Utils.DisplayText(entry.getValue().name + " ", 0.05f);
			}
		}
	}
	
}
