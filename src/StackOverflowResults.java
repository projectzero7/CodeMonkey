import java.util.List;

public class StackOverflowResults {
	// Declare variables
	private List<Item> items;
	private boolean has_more;
	private int quota_max;
	private int quota_remaining;
	
	// Getters
	public List<Item> getItems() { return items; }
	public boolean getHasMore() { return has_more; }
	public int getQuotaMax() { return quota_max; }
	public int getQuotaRemaining() { return quota_remaining; }
	
	// Setters
	public void setItems( List<Item> items ) { this.items = items; }
	public void setHasMore( boolean has_more ) { this.has_more = has_more; }
	public void setQuotaMax( int quota_max ) { this.quota_max = quota_max; }
	public void setQuotaRemaining( int quota_remaining ) { this.quota_remaining = quota_remaining; }
	
	// Convert the object information to a String
	public String toString() {
		String allItems = "";
		allItems.concat( "{items[ " );
		
		for (Item i : items) {
			allItems.concat( "{ " + i.toString() + " }" );
		}
		
		allItems.concat( " ], has_more: " + has_more + ", quota_max:" + quota_max + ", quota_remaining:" + quota_remaining + "}" );
				
		return allItems;
	}
	
	// Inner object Item
	static class Item {
		// Declare variables
		private Owner owner;
		private boolean is_accepted;
		private int score;
		private long last_activity_date;
		private long last_edit_date;
		private long creation_date;
		private int answer_id;
		private int question_id;
		private String body;
		
		// Getters
		//
		//
		//
		//
		//
		//
		//
		//
		public String getBody() { return body; }
		
		// Setters
		public void setOwner( Owner owner ) { this.owner = owner; }
		public void setIsAccepted( boolean is_accepted ) { this.is_accepted = is_accepted; }
		public void setScore( int score ) { this.score = score; }
		public void setLastActivityDate( long last_activity_date ) { this.last_activity_date = last_activity_date; }
		public void setLastEditDate( long last_edit_date ) { this.last_edit_date = last_edit_date; }
		public void setCreationDate( long creation_date ) { this.creation_date = creation_date; }
		public void setAnswerId( int answer_id ) { this.answer_id = answer_id; }
		public void setQuestionId( int question_id ) { this.question_id = question_id; }
		public void setBody( String body ) { this.body = body; }
		
		// Convert the object information to a String
		public String toString() { return "owner{" + owner + "}, "; }
	}
	
	// Inner object Owner
	static class Owner {
		// Declare variables
		private int reputation;
		private int user_id;
		private String user_type;
		private int accept_rate;
		private String profile_image;
		private String display_name;
		private String link;
		
		// Getters
		public int setReputation() { return reputation; }
		public int setUserId() { return user_id; }
		public String setUserType() { return user_type; }
		public int setAcceptRate() { return accept_rate; }
		public String setProfileImage() { return profile_image; }
		public String setDisplayName() { return display_name; }
		public String setLink() { return link; }
		
		// Setters
		public void setReputation( int reputation ) { this.reputation = reputation; }
		public void setUserId( int user_id ) { this.user_id = user_id; }
		public void setUserType( String user_type ) { this.user_type = user_type; }
		public void setAcceptRate( int accept_rate ) { this.accept_rate = accept_rate; }
		public void setProfileImage( String profile_image ) { this.profile_image = profile_image; }
		public void setDisplayName( String display_name ) { this.display_name = display_name; }
		public void setLink( String link ) { this.link = link; }
		
		// Convert the object information to a String
		public String toString() { return "reputation:" + reputation + ", user_id:" + user_id + ", user_type:" + user_type + ", accept_rate:" + accept_rate + ", profile_image:" + profile_image + ", display_name:" + display_name + ", link:" + link; }
	}
}
