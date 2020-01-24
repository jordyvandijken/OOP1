/**
 * This is the LockedExit class
 * It contains a boolean to indicate if the door is locked and the key required to open it.
 * 
 * @auhor Eva Jakobs
 * @verion 2020.1.23
 */

public class LockedExit extends Exit
{
	private boolean locked; // indicates exit lock. True means locked, false means unlocked
	private Item requiredKey; // if null,  exit can't be changed. 
	
	public LockedExit(String direction, Room neighbor, boolean locked, Item requiredKey)
	{
		this.locked = locked;
		this.setDirection(direction);
		this.setNeighbor(neighbor);
		this.requiredKey = requiredKey;
	}
	
	/*
	 * Lock this exit so players can't get through
	 */
	public void lock()
	
	{
		locked = true;
		return;
	}
	/*
	 * Unlock exit
	 */
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