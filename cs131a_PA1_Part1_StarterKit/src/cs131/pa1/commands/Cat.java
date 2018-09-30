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
	File file;
	
	// Constructor
	public Cat(String fileName) {
		file = new File(fileName);
		
		try {
			this.input = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process() {
		while (input.hasNextLine()) {
			System.out.println(input.nextLine());
		}
		
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}