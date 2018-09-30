package cs131.pa1.commands;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

public class ListDirectory extends SequentialFilter{
	
	public ListDirectory() {
		
	}

	

	@Override
	protected String processLine(String line) {
		output = new LinkedList<String>();
		StringBuilder fullPath = new StringBuilder();
		
		
		File folder = new File(line);
		
		
		File [] listOfFiles = folder.listFiles();
		
		listOfFiles = folder.listFiles();
		
		System.out.println("this is the line"  + line);
		
		System.out.println();
		
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals(".DS_Store")) {
		   // System.out.println( listOfFiles[i].getName());
		    output.add(listOfFiles[i].getName());
		    
		  } else if (listOfFiles[i].isDirectory() && !listOfFiles[i].getName().equals(".DS_Store")) {
		   // System.out.println(listOfFiles[i].getName());
		    output.add(listOfFiles[i].getName());
		    
		  }
		}
		return line;
	}
		
}
