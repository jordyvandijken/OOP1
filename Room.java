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
     * @param direction The direction
     * @param neighbour The room in that direction
     */
    public void setExit(String direction, Room neighbour)
    {
        exits.put(direction, neighbour);
    }

    /**
     * @return The short description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * @return The long description of the room.
     */
    public String getLongDescription()
    {
        return "You are" + description + ".\n" + getExitString();
    }
    
    /**
     * Shows all the existing exits
     * @return A list of all the exits in a string
     */
    private String getExitString()
    {
        String returnString = "Exits: ";
        Set <String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Get an exit of the room
     * @param direction The direction
     * @return return the room if exists else null
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * @return Gives the inventory of the room
     */
	public Inventory Inventory() {
		return inv;
	}

    /**
     * Set the inventory of the room
     * @param inv The Inventory to set
     */
	public void setInv(Inventory inv) {
		this.inv = inv;
	}
	
    /**
     * Prints the items and actors in the room
     */
	public void LookRoom() {
		Inventory().LookItems();
		
		LookActors();
	}
	
    /**
     * look if the room contain the exit
     * @param exit the room to check if it exists
     * @return true if the room exists
     */
	public boolean ContainsRoom(Room exit) {
		if (exits.containsValue(exit)) {
			return true;
		}
		
		return false;
	}

    /**
     * Add an actor to the room
     * @param _actor Adds actor to the room
     */
	public void AddActor (Actor _actor) {
		actors.put(_actor.name.toLowerCase(), _actor);
	}
	
    /**
     * Interact with the actor
     * @param _name the actor to interact with
     */
	public void Interact(String _name) {
		if (actors.containsKey(_name.toLowerCase())) {
			Utils.DisplayText(actors.get(_name.toLowerCase()).line, 0.05f);
		} else {
			Utils.DisplayText("There is no such person here!", 0.05f);
		}
	}
	
    /**
     * Get the actor with the given string
     * @param _name the name of the actor
     * @return the actor of it exists
     */
	public Actor GetActor(String _name) {
		if (actors.containsKey(_name.toLowerCase())) {
			return actors.get(_name.toLowerCase());
		} 
		return null;
	}
	
    /**
     * Print the actor of the room
     */
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
