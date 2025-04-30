package game;

import java.util.HashMap;

/**
 * Use.java
 * 
 * The command for using items for their intended function
 * 
 * @author Susannah Bennett, Kali Grose, and Steven Barker
 * Wheaton College, CSCI 245, Spring 2020
 * Project 4
 * March, 2020
 */
public class Use implements Command {
	
	/**
	 * The reference to the player
	 */
	private Player p1;
	
	/**
	 * Constructor
	 * 
	 * @param p1 The player
	 */
	public Use(Player p1) { this.p1 = p1; }
	
	/**
	 * When the command use is entered, this code runs and calls the item's function
	 * 
	 * @param command String[] that holds the string the user enters (from Parser.java)
	 */
	public void doSomething(String[] command) {
		HashMap<String, Item> items = p1.getItemList();
		if (!items.containsKey(command[1])) {
			System.out.println("Inventory does not contain this item");
			return;
		}
		items.get(command[1]).function(p1);
	}
	
	/**
	 * Returns the purpose of the use command
	 * 
	 * @return String of the purpose of the use command
	 */
	public String getDescription() {return "Use an item in your inventory"; }
}
