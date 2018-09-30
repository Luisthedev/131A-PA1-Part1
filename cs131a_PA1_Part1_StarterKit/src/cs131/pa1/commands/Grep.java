package cs131.pa1.commands;

import cs131.pa1.filter.sequential.SequentialFilter;

public class Grep extends SequentialFilter {

	// Fields
	String searchWord;
	
	// Constructor
	public Grep(String searchWord) throws MissingArgumentException {
		if (searchWord.equals(" ") || searchWord == null) {
			throw new MissingArgumentException();
		}
		this.searchWord = searchWord;
	}
	
	@Override
	protected String processLine(String line) {
		if (line.contains(this.searchWord)) {
			return line;
		}
		return null;
	}

}