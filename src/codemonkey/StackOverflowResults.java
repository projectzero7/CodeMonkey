package codemonkey;
import java.util.ArrayList;
import java.util.List;

public class StackOverflowResults {
	// Declare variables
	private List<Item> items;
	
	// Getters
	public List<Item> getItems() { return items; }
	public List<String> getBodies() {
		List<String> bodies = new ArrayList<String>();
		
		for (Item i : items) {
			bodies.add( i.getBody() );
		}
		
		return bodies;
	}
	
	// Inner object Item
	static class Item {
		// Declare variables
		private Owner owner;
		private String body;
		
		// Getters
		public Owner getOwner() { return owner; }
		public String getBody() { return body; }
	}
	
	// Inner object Owner
	static class Owner {
		// Declare variables
		private String display_name;
		
		// Getters
		public String getDisplayName() { return display_name; }
	}
}
