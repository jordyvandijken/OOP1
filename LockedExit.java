

public class LockedExit extends Exit
{
	private boolean locked;
	private Item requiredKey; // if null,  door lock can't be changed 
	
	public LockedExit(String direction, Room neighbor, boolean locked, Item requiredKey)
	{
		this.locked = locked;
		this.setDirection(direction);
		this.setNeighbor(neighbor);
		this.requiredKey = requiredKey;
	}
	
	public void lock()
	
	{
		locked = true;
		return;
	}
	
	public void unlock()
	
	{
		locked = false;
		return;
	}
	
	public boolean getLocked()
	{
		return locked;
		
	}
	
	public Item getRequiredKey()
	{
		return requiredKey;
	}
}