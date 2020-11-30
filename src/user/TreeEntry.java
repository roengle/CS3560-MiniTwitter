package user;

import visitor.TreeEntryVisitable;
import visitor.TreeEntryVisitor;

public abstract class TreeEntry implements TreeEntryVisitable{
	
	private String ID;
	private long creationTime;
	
	/**
	 * Constructs a TreeEntry object given an ID.
	 * 
	 * @param ID the ID of the TreeEntry
	 */
	public TreeEntry(String ID) { 
		this.ID = ID;
		this.creationTime = System.currentTimeMillis();
	}
	
	/**
	 * Gets the creation time of the tree entry
	 * 
	 * @return the creation time
	 */
	public long getCreationTime() { return this.creationTime; }
	
	/**
	 * Returns a string copy of the tree entry ID
	 * @return the ID in a new string
	 */
	public String getID() { return this.ID; }
	
	/**
	 * Gets the ID of the TreeEntry
	 */
	public String toString() { return ID; }
	
	/**
	 * Accepts a TreeEntryVisitor, which sends *this* to the visitor's visit method.
	 */
	public void accept(TreeEntryVisitor visitor) { visitor.visit(this); }
}