/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initializes all the others: it creates all
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
    Room lab, frontOfMansion, foyer, hallway, livingRoom, library, empty, grandHall, office, kitchen, mazeEntrance, maze1, maze2, maze3, maze4, maze5, maze6, mazeEnd;

    /**
     * Create the game and initialize its internal map.
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
    	
    
		        
 
    	lab = new Room("insde the lab");
        frontOfMansion = new Room("in front of a giant mansion");
        foyer = new Room("in the foyer");
        hallway = new Room("in a hallway");
        livingRoom = new Room("in the living room");
        library = new Room("in the library");
        grandHall = new Room("in the grand hall");
        office = new Room("in the office");
        kitchen = new Room("in the kitchen");
        
        mazeEntrance = new Room("at the start of the magic maze");
        maze1 = new Room ("in the first part of the maze");
        maze2 = new Room("in the second part of the maze");
        maze3 = new Room("in the third part of the maze");
        maze4 = new Room("in the fourth part of the maze");
        maze5 = new Room("in the fifth part of the maze");
        maze6 = new Room("in the sixth part of the maze");
        mazeEnd = new Room("at the end of the magic maze");
        
        // Add Items to the game
        
        Item coins = new Item("coins", 5, "Some coins");
        Item mansionKey = new Item("Mansion Key", 2, "Key to open the front door of the mansion");
        Item officeKey = new Item("Office Key", 2, "Key to open the office");
        Item painting = new Item("Painting", 50, "an oil painting. Due to its size, it's hard to carry");
        Item book = new Item("Book", 10, "An old book ");
        
                 
        
        kitchen.Inventory().AddItem(officeKey);
        livingRoom.Inventory().AddItem(coins);
        office.Inventory().AddItem(painting);
        office.Inventory().AddItem(book);
        maze4.Inventory().AddItem(mansionKey);
        
        
        // Add Actors to the game
        
        Actor steve = new Actor("Steve", "Hi, I'm Steve, your coworker. I have been looking for an old and valuable book that appears to be located in a jungle mansion. \n"
        		+ "Please, go on and find it.", "Congratulations! You finished the game. You can continue to wander around or enter quit to exit"); 
        
        steve.SetupTrading(book, new Item("finalItem", 1, "finalItem"));
        lab.AddActor(steve);
        
        
        // Initialize room exits
        lab.setExit("north", frontOfMansion);
              
        
        frontOfMansion.setExit("north", foyer);
        frontOfMansion.setExit("south", lab);
        foyer.setExit("north", grandHall);
        foyer.setExit("east", hallway);
        foyer.setLockedExit("south", frontOfMansion, true, mansionKey);
        foyer.setExit("west", kitchen);
        
       
        hallway.setExit("east", livingRoom);
        hallway.setExit("west", foyer);
        
        livingRoom.setExit("north", library);
        livingRoom.setExit("west", hallway);
        livingRoom.setExit("south", mazeEntrance);
        library.setExit("south", livingRoom);
        
        grandHall.setLockedExit("north", office, true, officeKey);
        grandHall.setExit("south", foyer);
        kitchen.setExit("east", foyer);
        office.setExit("south", grandHall);
        
        mazeEntrance.setExit("west", maze2);
        mazeEntrance.setLockedExit("up", livingRoom, true, null);
        
        maze1.setExit("north", maze3);
        maze1.setExit("east", maze4);
        maze1.setExit("south", maze6);
        
        maze2.setExit("east", maze1);
        maze2.setExit("south", maze5);
        maze2.setExit("west", maze4);
        
        maze3.setExit("north", maze2);
        maze3.setExit("east", maze5);
        maze3.setExit("west", mazeEnd);
        
        maze4.setExit("north", maze1);
        maze4.setExit("south", maze6);
        maze4.setExit("west", maze2);
        
        maze5.setExit("north", maze4);
        maze5.setExit("east", maze3);
        maze5.setExit("west", maze6);
        
        maze6.setExit("north", maze2);
        maze6.setExit("east", maze4);
        maze6.setExit("south", maze1);
        
        mazeEnd.setExit("up", kitchen);
         

        return lab;  // start game in the lab
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
        Utils.DisplayText("Welcome to the World of Zulu!", 0.05f);
        Utils.DisplayText("World of Zulu is a new, incredibly boring adventure game.", 0.05f);
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
        else if (commandWord.equals("unlock")) {
        	unlockExit(command);
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
        Utils.DisplayText("Go to the mansion and find the item Steve is looking for. When you find it, return to the lab and trade it with him", 0.05f);
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
    /*
     * Try to unlock door. If it fails, show why.
     */
    private void unlockExit(Command command)
    {
    	if(!command.hasSecondWord())
    	{
    		Utils.DisplayText("Unlock what?", 0.05f);
    		return;
    	}
    	    	
    	String wantedExit = command.getSecondWord(); // 
    	Item requiredKey = player.GetCurrentRoom().getActualExit(wantedExit).getRequiredKey();
    	
    	if(requiredKey == null)
    	{
    		Utils.DisplayText("This door can't be unlocked", 0.05f);
    	}
    	else {
    	
	    	if (player.Inventory().Contains(requiredKey)) // Check if player has the required key to unlock exit.
	    	{
	    		player.GetCurrentRoom().getActualExit(wantedExit).unlock(); 
	    		Utils.DisplayText("Door unlocked", 0.05f);
	    		
	    	}
	    	
	    	
	    	else {
	    		Utils.DisplayText("You don't have the right key. you need the " + requiredKey.GetName(), 0.05f);
	    	}
    	}
    	
    }
    
   
}
