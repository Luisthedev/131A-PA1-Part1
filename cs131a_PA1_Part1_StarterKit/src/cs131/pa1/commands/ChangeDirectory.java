package cs131.pa1.commands;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class ChangeDirectory extends SequentialFilter{
	String CDC;
	String newFullPath; 
	
	public ChangeDirectory(String CDC) throws IOException, MissingArgumentException {
		
		this.CDC = CDC;
		
		String newFullPath;
		
		if(CDC == null) {
			throw new MissingArgumentException();
		}
		
		String CWD = SequentialREPL.getCurrentDir();

	
	
	
		File newPath = new File(CDC);
	
	
		if (newPath.isAbsolute()){
			newFullPath = newPath.getCanonicalPath();
		
		} else {
			
			newFullPath = new File(CWD+FILE_SEPARATOR+newPath.getPath()).getCanonicalPath();
		}
		File f = new File (newFullPath);
 		if (!f.exists() || !f.isDirectory()){
 			throw new IOException();
 		}
 		

	}
	

	
	
	
	
	
	
	
	
	
	
	
	public void process(){
		SequentialREPL.changeCurrDirect(newFullPath);
		
	}

@Override
protected String processLine(String line) {
	// TODO Auto-generated method stub
	return null;
}
}
	
	
	
