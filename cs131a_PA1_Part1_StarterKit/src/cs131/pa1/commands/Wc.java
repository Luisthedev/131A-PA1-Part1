package cs131.pa1.commands;

import java.util.Scanner;

import cs131.pa1.filter.sequential.SequentialFilter;

public class Wc extends SequentialFilter {

	// Fields
	int numLines;
	int numWords;
	int numChars;
	
	public Wc() {
		this.numLines = 0;
		this.numWords = 0;
		this.numChars = 0;
	}
	
	@Override
	protected String processLine(String line) {
		// count chars
		numChars += line.length();

		// count words
		Scanner wordScan = new Scanner(line);
		while (wordScan.hasNext()) {
			numWords += 1;
		}

		// count lines
		numLines += 1;

		return (numLines + " " + numWords + " " + numChars);
	}

}
