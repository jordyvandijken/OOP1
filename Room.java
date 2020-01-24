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
    private HashMap <String, Exit> exits;
    private HashMap <String, LockedExit> lockedExits;

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
        exits = new HashMap<String, Exit>();
        actors = new HashMap<String, Actor>();
        lockedExits = new HashMap<String, LockedExit>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param The direction
     * @param The room in that direction
     */
    public void setExit(String direction, Room neighbor)
    {
    	Exit temp = new Exit(direction, neighbor);
        exits.put(direction, temp);
    }
    
    public void setLockedExit(String direction, Room neighbor, boolean locked, Item requiredKey)
    {
    	LockedExit temp = new LockedExit(direction, neighbor, locked, requiredKey);
    	lockedExits.put(direction, temp);
        
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
        return "You are " + description + ".\n" + getExitString() + getLockedExitString();
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
    
    private String getLockedExitString()
    {
    	if(!lockedExits.isEmpty())
    			{
    		String returnString = "\nLocked exits:";
        	Set<String> keys = lockedExits.keySet();
        	for(String lockedExit : keys)
        	{
        		returnString += " " + lockedExit;
        	}   	return returnString;
    		
    			}
    	else {
    		return "";
    	}
    	
    }
    
 
    
    public Room getExit(String direction)
    {
    	Exit tempExit = exits.get(direction);
    	if (tempExit != null)
    	{
    		return tempExit.getNeighbor();
    	}
    	return null;
    }
    
    public Room getLockedExit(String direction)
    {
    	LockedExit templocked = lockedExits.get(direction);
    	if(templocked != null)
    	{
    		return templocked.getNeighbor();
    		
    	}
    	return null;
    }
    
    public LockedExit getActualExit(String direction)
    {
    	return lockedExits.get(direction);
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
	
	public boolean getLocked(String direction)
	{
		return lockedExits.get(direction).getLocked();
	}
	
}
