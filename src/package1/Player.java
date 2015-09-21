package package1;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author tobydobbs
 * Details for the player and their character
 */
public class Player {
	
	String playerName;
	String charName;
	Room currentRoom;
	ArrayList<ITEM> items = new ArrayList<ITEM>(2);

	public Player(String name) {
		this.playerName = name;
		items.add(ITEM.MASK);
		getCharName();
	}
	
	/**
	 * The Joker will mockingly call his henchman by a unique patronising alias
	 */
	public void getCharName() {
		String[] henchman = {"Grumpy", "Happy", "Chuckles", "Dopey"};
		Random ran = new Random();
		charName =  henchman[ran.nextInt(4)];
	}
	
	/**
	 * Prints a horizontal list of the player's items
	 */
	public void printInventory() {
		for(int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i));
			if(i < items.size() - 1) { 
				System.out.print(", ");
			}
		} 
		System.out.println();
	}
	
	/**
	 * @param room
	 * @param item
	 * The player can use this method to acquire a new item. They can carry a maximum of two items at any one time.
	 */
	public void pickUp(ITEM item) {
		if(currentRoom.containsItem(item)) {
			if(items.size() < 2) {
				items.add(item);
				currentRoom.removeItem(item);
				System.out.println("\tYou acquired the " + item + "!");
			} else {
				System.out.println("\tYou can not carry more than two items.");
			}
		} else {
			System.out.println("\tThe room does not contain the " + item + ".");
		}
	}
	
	/**
	 * @param room
	 * @param item
	 * The player can rid themselves of any item at any time.
	 */
	public void drop(ITEM item) {
		if(items.contains(item)) {
			items.remove(item);
			currentRoom.addItem(item);
			System.out.println("\tYou dropped the " + item + " in the " + currentRoom + ".");
		} else {
			System.out.println("\tYou can not drop the " + item + " as you do not possess it.");
		}
	}
	
	/**
	 * @param direction
	 * Player can move from one room to another via a doorway
	 */
	public void moove(DIRECTION direction) {
		if(direction != null) {
			if(currentRoom.adjacents.containsKey(direction)) {
				if(!currentRoom.doors.get(direction).locked) {
					currentRoom = currentRoom.adjacents.get(direction);
					System.out.println("\tYou have moved to the " + currentRoom + ".");
				} else {
					System.out.println("\tThe door is locked.");
				}
			} else {
				System.out.println("\tYou can not move " + direction + " from this room.");
			}
		} else {
			System.out.println("\tYou can only move NORTH, EAST, SOUTH or WEST.");
		}
	}
	
	/**
	 * Allows the player to look within a room. This allows them to find objects or interact with characters.
	 */
	public void look() {
		int noItems;
		if((noItems = currentRoom.items.size()) == 0) {
			System.out.println("\tYou scan the room. There is nothing you can make any use of though.");
		} else if(noItems == 1) {
			System.out.println("\tThe " + currentRoom.items.get(0) + " is lying on the floor.");
		} else {
			System.out.print("\tYou scan the room. It contains: ");
			for(ITEM item : currentRoom.items) {
				System.out.print("The " + item + ". ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Player play1 = new Player("Toby");
		Room room1 = new Room("Butcher Shop", "A place to kill people in.");
		Room room2 = new Room("Knife Shop", "A place to buy knives to kill people with.");
		//room1.north = room2;
		//room2.south = room1;
		
		play1.currentRoom = room1;
		
		room1.addItem(ITEM.GUN);
		room2.addItem(ITEM.MASK);
		System.out.println("You are: " + play1.charName);
		
		play1.pickUp(ITEM.GUN);
		room1.printRoomContents();
		play1.drop(ITEM.GUN);
		room1.printRoomContents();
		play1.pickUp(ITEM.GUN);
		play1.moove(DIRECTION.NORTH);
		play1.moove(DIRECTION.EAST);
		room2.printRoomContents();
		play1.look();
		play1.pickUp(ITEM.MASK);
		play1.printInventory();
	}

}
