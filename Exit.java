
public class Exit
{
	private String direction;
	private Room neighbor;
	
	
	public Exit()
	{
	}
	
	public Exit(String direction, Room neighbor)
	{
		this.direction = direction;
		this.neighbor = neighbor;
		
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public Room getNeighbor() 
	{
		return neighbor;
	}
	
	public void setDirection(String direction)
	{
		this.direction = direction;
		
	}
	
	public void setNeighbor(Room neighbor)
	{
		this.neighbor = neighbor;
	}
	
}