package package1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tobydobbs
 * See game blurb (Blurb.txt)
 */
public class TheHeist {
	
	World theWorld;
	Player player;
	Scanner s;
	int noActions = 0; //keeps track of the number of actions performed
	boolean task1 = false, task2 = false, task3 = false; //boolean values indicate whether each task has been completed

	public TheHeist() {
		s = new Scanner(System.in);
		setScene("txt-files/Epilogue");
		signIn();
		build();
		setScene("txt-files/Task1");
		open();
		s.close();
	}
	
	/**
	 * Check the completion of tasks
	 */
	public void checkList() {
		if(task1 == false) 
			task1();
		else if(task2 == false) 
			task2();
		else if(task3 == false) 
			task3();
		else
			System.out.println("You completed the heist.");
	}
	
	/**
	 * Get to the bank
	 */
	public void task1() {
		if(player.currentRoom.roomName.equals("Gotham National Bank (Main Hall)")) {
			System.out.println("\n\tYOU SUCCESFULLY COMPLETED TASK 1!");
			task1 = true;
			setScene("txt-files/Task2");
			theWorld.rooms.get(2).doors.get(DIRECTION.EAST).unlock();
			theWorld.rooms.get(3).doors.get(DIRECTION.WEST).unlock();
		}
	}
	
	/**
	 * Use grenades to force control over the hostages. Keep identity hidden. 
	 */
	public void task2() {
		if(player.items.contains(ITEM.GRENADES) && player.currentRoom.roomName.equals("Gotham National Bank (Main Hall)")) {
			System.out.println("\n\tYOU SUCCESFULLY COMPLETED TASK 2!");
			task2 = true;
			setScene("txt-files/Task3");
		}
	}
	
	/**
	 * Escape via the school bus
	 */
	public void task3() {
		if(player.currentRoom.roomName.equals(("School Bus"))) {
			System.out.println("\n\tYOU SUCCESFULLY COMPLETED TASK 3!");
			task3 = true;
		}
	}
	
	/**
	 * Opens the thread to accept commands from the user
	 */
	public void open() {
		String command;
		String regex = "(^(PICK-UP|DROP|MOVE)\\s[A-Z]+$)|LOOK|HELP";
		String regex2 = " ";
		Pattern p1 = Pattern.compile(regex);
		Pattern p2 = Pattern.compile(regex2);
		while(!(command = s.nextLine()).equals("QUIT")) {
			if(noActions < 50) {
				Matcher m = p1.matcher(command);
				while(m.find()) {
					executeCommand(p2.split(command));
				}
				noActions++;
				checkList();
			} else {
				setScene("txt-files/Game-over");
				break;
			}
		}
		System.out.println("\tBANG!!! Ha ha ha ha ha ho ho ho he he!\n\tYou quit the game.");
	}
	
	/**
	 * @param tokens
	 * Execute a command based on user-input
	 */
	public void executeCommand(String[] tokens) {
		String first = tokens[0];
		if(first.equals("MOVE")) {
			try {
				player.moove(DIRECTION.valueOf(tokens[1]));
			} catch (IllegalArgumentException e) {
				System.err.println("\t'" + tokens[1] + "' is not a valid direction.");
			}
		} else if(first.equals("PICK-UP")) {
			try {
				player.pickUp(ITEM.valueOf(tokens[1]));
			} catch (IllegalArgumentException e) {
				System.err.println("\tThat is not a valid item.");
			}
		} else if(first.equals("LOOK")) {
			player.look();
		} else if(first.equals("DROP")) {
			try {
				player.drop(ITEM.valueOf(tokens[1]));
			} catch (IllegalArgumentException e) {
				System.err.println("\tThat is not a valid item.");
			}
		} else if(first.equals("HELP")) {
			help();
		}
	}
	
	/**
	 * Builds the environment
	 * Rooms
	 * Items
	 * Geography
	 * Room A should be the default room
	 */
	public void build() {
		theWorld = new World("Gotham City");
		
		Room A = new Room("West Van Buren St", "A busy street on a typical Spring day in Gotham.");
		Room B = new Room("Van", "A white mini-van driven by one of the Joker's henchman. Another sits to his left.");
		Room C = new Room("Gotham National Bank (Main Hall)", "The client-side of the bank. A row of clerks are seated\n"
				+ "against the far wall which runs along approxiamately 30m.\n"
				+ "A glass office (occupied by the Manager) is positioned near the entrance.\n"
				+ "A row of tables, seperated by 3m (approx.) gaps is positioned along the spine.\n"
				+ "Natural light enters the hall through large windows on the wall facing out on to the street.");
		Room D = new Room("Gotham National Bank (Outside Safe)", "A small day-lit room underneath the main hall.\n"
				+ "A locked door protects the safe from being breached.");
		Room E = new Room("Gotham National Bank (Inside Safe)", "A large room stacked with cash.");
		Room F = new Room("School Bus", "A typical American school bus painted yellow");
		
		A.adjacents.put(DIRECTION.NORTH, B);
		B.adjacents.put(DIRECTION.EAST, C);
		C.adjacents.put(DIRECTION.NORTH, F);
		C.adjacents.put(DIRECTION.EAST, D);
		D.adjacents.put(DIRECTION.NORTH, E);
		D.adjacents.put(DIRECTION.WEST, C);
		E.adjacents.put(DIRECTION.WEST, D);
		
		theWorld.addRoom(A);
		theWorld.addRoom(B);
		theWorld.addRoom(C);
		theWorld.addRoom(D);
		theWorld.addRoom(E);
		theWorld.addRoom(F);
		
		A.addItem(ITEM.KNIFE);
		B.addItem(ITEM.CAR_KEYS);
		B.addItem(ITEM.BULLETPROOF_VEST);
		A.addItem(ITEM.GRENADES);
		E.addItem(ITEM.GUN);
		D.addItem(ITEM.DRILL);
		
		A.createDoorway(B, false);
		B.createDoorway(C, false);
		C.createDoorway(D, true);
		C.createDoorway(F, true);
		D.createDoorway(C, true);
		D.createDoorway(E, true);
		E.createDoorway(D, true);
		
		player.currentRoom = A; //Start
	}
	
	/**
	 * The player is introduced to the game
	 */
	public void signIn() {
		System.out.println("\tJoker:\n"
				+ "\t\tTell me your name?");
		String name = s.nextLine();
		player = new Player(name);
		System.out.println("\tJoker:\n"
				+ "\t\tOkay " +  player.charName + ", is that how you say it? He he he!\n"
				+ "\t\tWelcome to the team.\n"
				+ "\t\tJust so you know you'll need to use CAPS for all commands in this game.\n"
				+ "\t\tType HELP to get some assistance from me or QUIT to leave - but then why would ya wanna do that?!\n"
				+ "\t\tHa ha ha!\n");
	}
	
	/**
	 * When the player types the keyword 'help' 
	 * they will be presented with details concerning the current state of the game
	 */
	public void help() {
		System.out.println("\tYou need help huh?");
		System.out.println("\tSelect a topic from the following: COMMANDS, CHARACTERS, ITEMS, SUMMARY");
		String command;
		while(!(command = s.nextLine()).equals("EXIT")) {
			if(command.equals("COMMANDS")) {
					commandInfo();			
			} else if(command.equals("CHARACTERS")) {
				System.out.println("\tType in the name of the character you wish to know more about...");
				charInfo(s.nextLine());
			} else if(command.equals("ITEMS")) {
				System.out.print("\tYou currenty possess the following items: ");
				player.printInventory();
			} else if(command.equals("SUMMARY")){
				getStatus();
			} else {
				System.out.println("\tThe command '" + command + "' is not recognized. Type 'EXIT' to leave the help menu.");
			}
		}
	}
	
	/**
	 * Provides assistance to the player in terms of how to play the game
	 */
	public void commandInfo() {
		System.out.println("\tThe following commands are at your disposal:\n\n"
				+ "\t\tPICK-UP [item]: Allows you to acquire an item. \n"
					+ "\t\tRemember to replace [item] with the name of the object you wish to collect.\n"
				+ "\t\tDROP [item]: Allows you to free yourself of an object.\n"
					+ "\t\tRemeber to replace [item] with the name of the object you wish to drop.\n"
				+ "\t\tMOVE [NORTH | SOUTH | EAST | WEST]: Allows you to move between ADJACENT rooms.\n"
				+ "\t\tLOOK [NORTH | SOUTH | EAST | WEST]: Allows you to look within a room.");
	}
	
	/**
	 * Provides the player with some basic info about each of the characters
	 */
	public void charInfo(String name) {
		List<String> characters = Arrays.asList("Joker", "Happy", "Chuckles", "Dopey", "Grumpy");
		if(characters.contains(name)) {
			setScene("txt-files/" + name);
		} else {
			System.out.println("\tThe character '" + name + "' is not recognised in this game.");
		}
	}
	
	/**
	 * Provides the player with some basic info about the status of the game
	 * Player name, Character name, Current room, Adjacent rooms
	 */
	public void getStatus() {
		System.out.println("\tYour name: " + player.playerName + "\n"
					+ "\tYour character's name: " + player.charName + "\n"
					+ "\tYou are in: " + player.currentRoom + "\n");
	}
	
	/**
	 * Describes the scene at the beginning of a new chapter in the story
	 */
	public void setScene(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			System.out.println();
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println();
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("setScene(): The filename given could not be matched.");
		} catch (IOException e) {
			System.err.println("setScene(): The readLine() function failed.");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("The Heist - original text adventure written by Toby Dobbs.");
		System.out.println("Inspired by Christopher Nolan's, THE DARK KNIGHT \u00a9.\n");
		
		@SuppressWarnings("unused")
		TheHeist game1 = new TheHeist();
	}

}
