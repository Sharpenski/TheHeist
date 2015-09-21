package package1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tobydobbs
 * A room in the game is where the player can interact with the environment
 * Here they may collect items or converse with another character
 */
public class Room {
	
	String roomName;
	String description = "A description has not been added.";
	ArrayList<ITEM> items = new ArrayList<ITEM>(); //items within the room
	Map<DIRECTION, Room> adjacents = new HashMap<DIRECTION, Room>(4); //adjacent rooms
	Map<DIRECTION, Door> doors = new HashMap<DIRECTION, Door>(); //available doorways

	public Room(String roomName, String description) {
		this.roomName = roomName;
		if(description != null) {
			this.description = description;
		}
	}
	
	public void createDoorway(Room to, boolean locked) {
		if(adjacents.containsValue(to)) {
			doors.put(Utility.getKey(to, adjacents), new Door(this, to, locked));
		} else {
			System.err.println("You can not place a doorway between non-adjacent rooms.");
		}
	}
	
	/**
	 * @param item
	 * Remove an item from the room
	 */
	public void removeItem(ITEM item) {
		items.remove(item);
	}
	
	/**
	 * @param item
	 * Adds an item to the room
	 */
	public void addItem(ITEM item) {
		items.add(item);
	}
	
	/**
	 * @param item
	 * @return boolean value indicating whether the room contains the requested item
	 */
	public boolean containsItem(ITEM item) {
		if(items.contains(item)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Print out the contents of a the room
	 */
	public void printRoomContents() {
		if(isEmpty() == false) {
			System.out.println("\t" + roomName +" contains the following items:");
			for(ITEM item : items) {
				System.out.println("\t\t-> " + item);
			}
		} else {
			System.out.println("\tThe room " + roomName + " does not contain any items.");
		}
	}
	
	/**
	 * @return boolean value to indicate whether the room contains an items
	 */
	public boolean isEmpty() {
		if(items.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return boolean value to indicate whether the room has another to its north
	 */
	public boolean hasNorth() {
		return adjacents.containsKey(DIRECTION.NORTH);
	}
	
	/**
	 * @return boolean value to indicate whether the room has another to its east
	 */
	public boolean hasEast() {
		return adjacents.containsKey(DIRECTION.EAST);
	}
	
	/**
	 * @return boolean value to indicate whether the room has another to its south
	 */
	public boolean hasSouth() {
		return adjacents.containsKey(DIRECTION.SOUTH);
	}
	
	/**
	 * @return boolean value to indicate whether the room has another to its west
	 */
	public boolean hasWest() {
		return adjacents.containsKey(DIRECTION.WEST);
	}
	
	/**
	 * @param direction
	 * @return room in the specified direction or null if does not exist
	 */
	public Room getAdj(DIRECTION direction) {
		if(hasAdj(direction)) {
			if(hasNorth())
				return adjacents.get(DIRECTION.NORTH);
			if(hasEast())
				return adjacents.get(DIRECTION.EAST);;
			if(hasSouth())
				return adjacents.get(DIRECTION.SOUTH);;
			if(hasWest())
				return adjacents.get(DIRECTION.WEST);;
		}
		return null;
	}
	
	/**
	 * @param direction
	 * @return boolean value to indicate whether the room as an adjacent room in the given direction
	 */
	public boolean hasAdj(DIRECTION direction) {
		if(direction == DIRECTION.NORTH) {
			return hasNorth();
		} else if(direction == DIRECTION.EAST) {
			return hasEast();
		} else if(direction == DIRECTION.SOUTH) {
			return hasSouth();
		} else {
			return hasWest();
		}
	}
	
	@Override
	public String toString() {
		return roomName;
	}

}
