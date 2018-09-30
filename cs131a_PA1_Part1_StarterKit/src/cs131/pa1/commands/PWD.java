package cs131.pa1.commands;

import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class PWD extends SequentialFilter {
	
	public PWD() {
		
	}

	@Override
	public void process(){
		this.output.add(SequentialREPL.getCurrentDir());
	}
	
	@Override
 	protected String processLine(String line) {
 		// Should never be called, as we have overridden process.
 		return null;
 	}
 }
