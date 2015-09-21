package package1;

/**
 * @author tobydobbs
 * Class to represent a doorway between rooms
 */
public class Door {
	
	Room from, to;
	boolean locked;

	public Door(Room from, Room to, boolean locked) {
		this.from = from;
		this.to = to;
		this.locked = locked;
	}
	
	/**
	 * Lock the door
	 */
	public void lock() {
		locked = true;
	}
	
	/**
	 * Unlock the door
	 */
	public void unlock() {
		locked = false;
	}
	
	public String toString() {
		return "From: " + from + " - To: " + to;
	}

}
