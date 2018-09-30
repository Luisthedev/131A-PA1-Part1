package cs131.pa1.commands;

import cs131.pa1.filter.sequential.SequentialFilter;

public class Grep extends SequentialFilter {

	// Fields
	String searchWord;
	
	// Constructor
	public Grep(String searchWord) {
     	if (searchWord.contains(" ")) {
			// Missing Argument Error
		} else if (searchWord == null) {
			// Missing Argument Error
		} else {
			this.searchWord = searchWord;
		}
	}
	
	@Override
	protected String processLine(String line) {
		if (line.contains(searchWord)) {
			return line;
		} else return null;
	}
	
	public String toString() {
		return searchWord;
		
	}

}
