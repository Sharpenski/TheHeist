package package1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tobydobbs
 * Model of the gaming world
 */
public class World {
	
	String name;
	Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	int noRooms = 0;

	public World(String name) {
		this.name = name;
	}
	
	/**
	 * @param toAdd
	 * Place a new room in the world
	 */
	public void addRoom(Room room) {
		rooms.put(noRooms++, room);
	}

}
