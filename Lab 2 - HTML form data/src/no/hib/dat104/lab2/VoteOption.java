package no.hib.dat104.lab2;

public class VoteOption {
	private String description;
	private int count;
	
	public VoteOption(String description) {
		this.description = description;
		count = 0;
	}
	
	public synchronized void addVote() {
		++count;
	}
	
	public int getVotes() {
		return count;
	}
	
	public String toString() {
		return description + ": " + count + " stemmer";
	}
}
