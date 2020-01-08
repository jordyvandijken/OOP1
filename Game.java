/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
	@SuppressWarnings("unused")
	public static void main (String[] args) {
		Game game = new Game();
		game.play();
	}
	
	
    private Parser parser;
    private Player player;
    Room outside, theater, pub, lab, office;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        Room startRoom = CreateRooms();
        parser = new Parser();
        player = new Player(startRoom, 100);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room CreateRooms()
    {      
        // create the rooms
    	
    	// testing room
        outside = new Room("outside the main entrance of the university");
		Item test = new Item("Test", 1, "This is a Item to test the inventory");
        outside.Inventory().AddItem(test);
        
        Actor testActor = new Actor("Bob", "My name is Bob and I'm here to test this actor thing", "Thank you for the Test item Here is Test item 2"); 
        testActor.SetupTrading(test, new Item("Test2", 1, "This is an item to test actor trading"));

        outside.AddActor(testActor);
        
        
        // Other rooms
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        return outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + player.GetCurrentRoom().getDescription());
        System.out.print("Exits: ");
        if(player.GetCurrentRoom().northExit != null) {
            System.out.print("north ");
        }
        if(player.GetCurrentRoom().eastExit != null) {
            System.out.print("east ");
        }
        if(player.GetCurrentRoom().southExit != null) {
            System.out.print("south ");
        }
        if(player.GetCurrentRoom().westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            player.GetCurrentRoom().LookRoom();;
        }
        else if (commandWord.equals("pickup")) {
            PickItem(command);
        }
        else if (commandWord.equals("drop")) {
            DropItem(command);
        }
        else if (commandWord.equals("bag")) {
            player.Inventory().LookItems();
        }
        else if (commandWord.equals("meeting")) {
        	if (command.hasSecondWord()) {
                player.GetCurrentRoom().Interact(command.getSecondWord());				
			} else {
				System.out.println("Who do you want to meet?");
			}
        } 
        else if (commandWord.equals("trade")) {
			Trade(command);
		}

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        player.GotoRoom(direction);
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public boolean PickItem(Command command) {
    	if(!command.hasSecondWord()) {
            System.out.println("Pick what?");
            return false;
        }
        else {
        	System.out.println(command.getSecondWord());
        	player.PickUpItem(command.getSecondWord());
            return true; 
        }
    }
    
    public boolean DropItem(Command command) {
    	if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return false;
        }
        else {
        	System.out.println(command.getSecondWord());
        	player.PickUpItem(command.getSecondWord());
            return true; 
        }
    }
    
    public void Trade(Command command) {
    	if (command.hasSecondWord()) {
			if (command.hasThirdWord()) {
				Item tradeItem = player.GetCurrentRoom().GetActor(command.getSecondWord()).Trade(player.Inventory().GetItem(command.getThirdWord()));
				
				if (tradeItem == null) {
					return;
				}
				
				System.out.println(player.GetCurrentRoom().GetActor(command.getSecondWord()).finishedLine);
				
				if (player.Inventory().AddItem(tradeItem)) {
					player.Inventory().RemoveItem(player.Inventory().GetItem(command.getThirdWord()));
				} else {
					player.GetCurrentRoom().Inventory().AddItem(tradeItem);
					player.Inventory().RemoveItem(player.Inventory().GetItem(command.getThirdWord()));
				}
			} else {
				System.out.println("Trade what?");
			}
		} else {
			System.out.println("Trade with who?");
		}
    }
}
