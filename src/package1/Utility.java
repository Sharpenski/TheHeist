package package1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

/**
 * @author tobydobbs
 * Some helper methods for the game
 */
public class Utility {

	/**
	 * @param direction
	 * @return A string value to represent the direction
	 */
	public static String dirToString(DIRECTION direction) {
		if(direction == DIRECTION.NORTH)
			return "NORTH";
		if(direction == DIRECTION.EAST)
			return "EAST";
		if(direction == DIRECTION.SOUTH)
			return "SOUTH";
		return "WEST";
	}
	
	/**
	 * @param direction
	 * @return Direction matching string provided
	 */
	public static DIRECTION stringToDir(String direction) {
		if(direction.equals("NORTH")) 
			return DIRECTION.NORTH;
		if(direction.equals("EAST")) 
			return DIRECTION.EAST;
		if(direction.equals("SOUTH")) 
			return DIRECTION.SOUTH;
		if(direction.equals("WEST")) 
			return DIRECTION.WEST;
		return null;
	}
	
	/**
	 * @param args
	 * USED TO TEST SOME REGEX
	 */
	public void test() {
		System.out.println("Main method of Utility...");
		Scanner s = new Scanner(System.in);
		ArrayList<String> tokens = new ArrayList<String>();
		String token;
		String regex = "(^(PICK\\sUP|DROP|MOVE)\\s[A-Z]+$)|LOOK";
		Pattern p = Pattern.compile(regex);
		while(!(token = s.nextLine()).equals("QUIT")) {
		Matcher m = p.matcher(token);
			while(m.find()) {
				System.out.println("SUCCESS!");
				tokens.add(token);
			}
		}
		System.out.println(tokens);
	}
	
	/**
	 * @param map
	 * @return key associated with value - like an inverse map; null if the value is not found
	 */
	public static <K, V>  K getKey(V value, Map<K,V> map) {
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<K, V> pair = it.next();
			if(pair.getValue().equals(value)) {
				return pair.getKey();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> Scotland = new HashMap<Integer, String>();
		Scotland.put(1, "Edinburgh");
		Scotland.put(2, "Elgin");
		Scotland.put(3, "Duffus");
		Scotland.put(4, "Dalmeny");
		System.out.println(Utility.getKey("Elgin", Scotland));
	}

}
