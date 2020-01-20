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
    Room lab, jungle, frontOfMansion, foyer, hallway, livingRoom, library, empty, entranceHall, office, kitchen, maze1, maze2, maze3, maze4, maze5, maze6;

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
        lab = new Room(" insde the lab");
		Item test = new Item("Test", 1, "This is a Item to test the inventory");
		lab.Inventory().AddItem(test);
        
        Actor testActor = new Actor("Bob", "My name is Bob and I'm here to test this actor thing", "Thank you for the Test item Here is Test item 2"); 
        testActor.SetupTrading(test, new Item("Test2", 1, "This is an item to test actor trading"));

        lab.AddActor(testActor);
        
        
        // Other rooms
        jungle = new Room("in an open space in the jungle");
        frontOfMansion = new Room("in front of a giant mansion");
        foyer = new Room("in the foyer");
        hallway = new Room("in a hallway");
        livingRoom = new Room("in the living room");
        library = new Room("in the library");
        empty = new Room("in an empty room");
        entranceHall = new Room("in the grand entrance hall");
        office = new Room("in the office");
        kitchen = new Room("in the kitchen");
        
        maze1 = new Room ("you are in the first part of the maze");
        maze2 = new Room("you are in the second part of the maze");
        maze3 = new Room("you are in the third part of the maze");
        maze4 = new Room("you are in the fourth part of the maze");
        maze5 = new Room("you are in the fifth part of the maze");
        maze6 = new Room("you are in the sixth part of the maze");
        
        // Initialize room exits
        lab.setExit("north", jungle);
        jungle.setExit("north", frontOfMansion);
        frontOfMansion.setExit("north", foyer);

        foyer.setExit("north", entranceHall);
        foyer.setExit("east", hallway);
        foyer.setExit("west", kitchen);
        
       
        hallway.setExit("east", livingRoom);
        hallway.setExit("west", foyer);
        
        livingRoom.setExit("north", library);
        livingRoom.setExit("west", hallway);
        library.setExit("south", livingRoom);
        
        entranceHall.setExit("north", office);
        entranceHall.setExit("south", foyer);
        kitchen.setExit("east", foyer);
        office.setExit("south", entranceHall);
        
        maze1.setExit("north", maze3);
        maze1.setExit("east", maze4);
        maze1.setExit("south", maze6);
        
        maze2.setExit("east", maze1);
        maze2.setExit("south", maze5);
        maze2.setExit("west", maze4);
        
        maze3.setExit("north", maze2);
        maze3.setExit("east", maze5);
        maze3.setExit("west", kitchen);
        
        maze4.setExit("north", maze1);
        maze4.setExit("south", maze6);
        maze4.setExit("west", maze2);
        
        maze5.setExit("north", maze4);
        maze5.setExit("east", maze3);
        maze5.setExit("west", maze6);
        
        maze6.setExit("north", maze2);
        maze6.setExit("east", maze4);
        maze6.setExit("south", maze1);

        return lab;  // start game outside
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
        Utils.DisplayText("Thank you for playing.  Good bye.", 0.05f);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        Utils.DisplayText("", 0);
        Utils.DisplayText("Welcome to the World of Zuul!", 0.05f);
        Utils.DisplayText("World of Zuul is a new, incredibly boring adventure game.", 0.05f);
        Utils.DisplayText("Type 'help' if you need help.", 0.05f);
        Utils.DisplayText("", 0);
        Utils.DisplayText(player.GetCurrentRoom().getLongDescription(), 0.05f);
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
            Utils.DisplayText("I don't know what you mean...", 0.05f);
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
	            Utils.DisplayText("Who do you want to meet?", 0.05f);
			}
        } 
        else if (commandWord.equals("trade")) {
			Trade(command);
		}
        else if (commandWord.equals("back")) {
			Back(command);
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
        Utils.DisplayText("You are lost. You are alone. You wander", 0.05f);
        Utils.DisplayText("around at the university.", 0.05f);
        Utils.DisplayText("", 0.05f);
        Utils.DisplayText("Your command words are:", 0.05f);

        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            Utils.DisplayText("Go where?", 0.05f);

            return;
        }
        player.GotoRoom(command.getSecondWord().toLowerCase());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void Back(Command command) 
    {
        player.GotoLastRoom();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            Utils.DisplayText("Quit what?", 0.05f);

            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public boolean PickItem(Command command) {
    	if(!command.hasSecondWord()) {
            Utils.DisplayText("Pick what?", 0.05f);

            return false;
        }
        else {
        	player.PickUpItem(command.getSecondWord());
            return true; 
        }
    }
    
    public boolean DropItem(Command command) {
    	if(!command.hasSecondWord()) {
            Utils.DisplayText("Drop what?", 0.05f);

            return false;
        }
        else {
        	
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
				
				Utils.DisplayText(player.GetCurrentRoom().GetActor(command.getSecondWord()).finishedLine, 0.05f);
				
				if (player.Inventory().AddItem(tradeItem)) {
					player.Inventory().RemoveItem(player.Inventory().GetItem(command.getThirdWord()));
				} else {
					player.GetCurrentRoom().Inventory().AddItem(tradeItem);
					player.Inventory().RemoveItem(player.Inventory().GetItem(command.getThirdWord()));
				}
			} else {
				Utils.DisplayText("Trade what?", 0.05f);
			}
		} else {
			Utils.DisplayText("Trade with who?", 0.05f);
		}
    }
}
