package cs131.pa1.commands;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

public class uniq extends SequentialFilter {

	// Fields
	LinkedList<String> seenStorage; 
	public uniq() {
		this.seenStorage = new LinkedList<String>();
	}

	@Override
	protected String processLine(String line) {
		if (!this.seenStorage.contains(line)) {
			seenStorage.add(line);
			return line;
		}
		return null;
	}

}