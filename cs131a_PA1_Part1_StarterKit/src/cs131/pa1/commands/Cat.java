package cs131.pa1.commands;

import java.util.Scanner;
import cs131.pa1.filter.sequential.SequentialFilter;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * Output the entirety of one or more files to the output message queue
 */

public class Cat extends SequentialFilter{
	
	//Fields
	Scanner input;
	// Constructor
	
	String fileName;
	public Cat(String fileName)  {
		if (fileName == null) {
			//Missing argument message
		}
		
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "cat" +fileName;
		
	}
	
	
	
	

}
