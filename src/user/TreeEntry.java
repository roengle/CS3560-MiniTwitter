package user;

public abstract class TreeEntry {
	private String ID;
	
	public TreeEntry(String ID) {
		this.ID = ID;
	}
	
	/**
	 * Returns a string copy of the tree entry ID
	 * @return the ID in a new string
	 */
	public String getID() {
		//Don't return a direct reference to ID
		return String.copyValueOf(ID.toCharArray());
	}
	
	public String toString() {
		return ID;
	}
}
