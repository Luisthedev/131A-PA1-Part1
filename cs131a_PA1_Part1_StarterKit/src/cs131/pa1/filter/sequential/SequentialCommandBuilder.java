package cs131.pa1.filter.sequential;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs131.pa1.commands.Cat;
import cs131.pa1.commands.ChangeDirectory;
import cs131.pa1.commands.FilePrinter;
import cs131.pa1.commands.Grep;
import cs131.pa1.commands.ListDirectory;
import cs131.pa1.commands.MissingArgumentException;
import cs131.pa1.commands.OutPrinter;
import cs131.pa1.commands.PWD;
import cs131.pa1.commands.Wc;
import cs131.pa1.commands.uniq;
import cs131.pa1.filter.Message;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		String [] listOfCommands = multipleCMDProccess(command);
		SequentialFilter comstructFinalFilter = determineFinalFilter(command);
		command = adjustCommandToRemoveFinalFilter(command);	
		SequentialFilter prevFilter;
		
		String subCommand [] =  command.split("\\|");
		
		List<SequentialFilter> pipes = new ArrayList<SequentialFilter>();

		
		List<SequentialFilter> commandList = new ArrayList<SequentialFilter>();
 		
 		for(int i = 0; i < subCommand.length; i++) {
 			commandList.add(determineFinalFilter(listOfCommands[i]));
 			
 			SequentialFilter subFilter = constructFilterFromSubCommand(subCommand[i].trim());
			
 			if(!pipes.isEmpty()) {
 				prevFilter = pipes.get(pipes.size()-1);
 			}

 		}
 		
 		
 		 
 		
		return commandList;
	}
	
	
	private static SequentialFilter determineFinalFilter(String command){
		
		if (command.contains(">")){
			String fileName = command.substring(command.lastIndexOf(">") + 1).trim();
			return new FilePrinter(fileName);
 		}
		
		
 		return new OutPrinter();
 	}

		
			
	
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		
		if (command.contains(">")){
 			return command.substring(0, command.lastIndexOf(">"));
 		} else {
 			return command;
		}
		
		
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand) {
		String[] parse = subCommand.split(" ",2);
		String commandIn = parse[0].toLowerCase();

		
		if(parse.length >1) {
			return null;
		}
		String param = parse[1];
		
		if(commandIn.equals("cd")) {
			try {
 				return new ChangeDirectory(param);
 			} catch (IOException e) {
 				return new OutPrinter(Message.DIRECTORY_NOT_FOUND.with_parameter(subCommand));
 			} catch (MissingArgumentException e) {
 				return new OutPrinter(Message.REQUIRES_PARAMETER.with_parameter(subCommand));
 			}
			
		}else if(commandIn.equals("ls")) {
			return new ListDirectory();
			
		}else if(commandIn.equals("pwd")) {
			return new PWD();
			
		}else if(commandIn.equals("Wc")) {
			return new Wc();
			
		}else if(commandIn.equals("uniq")) {
			return new uniq();
			
		}else if(commandIn.equals("grep")) {
			
			try {
 				return new Grep(param);
 			} catch (MissingArgumentException e) {
 				return new OutPrinter(Message.REQUIRES_INPUT.with_parameter(subCommand));
 			}
			
		}else if(commandIn.equals("cat")  ) {
			try {
 				return new Cat(param);
 			//}// catch (FileNotFoundException e) {
 			//	return new OutPrinter(Message.FILE_NOT_FOUND.with_parameter(subCommand));
 			} catch (MissingArgumentException e) {
 				return new OutPrinter(Message.REQUIRES_INPUT.with_parameter(subCommand));
 			}
			
			
		}else {
			return new OutPrinter(Message.COMMAND_NOT_FOUND.with_parameter(subCommand));
		}
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static boolean linkFilters(List<SequentialFilter> filters){
		
		for(int i = 0; i < filters.size()-1; i++) {
			
			if(filters.get(i+1) != null) {
				filters.get(i).setNextFilter(filters.get(i+1));
				
			}
			
		}
		filters.get(filters.size()-1).setPrevFilter(filters.get(0));
		
		return true;
	}
	
	
	
	
	
	private static boolean mutipleCMD(String command) {
		if(command.contains("|")  || command.contains(">")) {
			return true;
		}else {
			return false;
		}
	}
	
	private static String [] multipleCMDProccess(String command ) {
		if(command.contains(" >") && command.contains("|")) {
			//System.out.println("hello");
			
			String [] cmdParse = command.split(" >|\\|");
			String [] cc = command.split("\\|");
			
			
			for(int i = 0; i <cmdParse.length; i++) {
				//SysteSystem.out.println(i + " " + cmdParse[i]);
			}
			cmdParse[1] = ">"+cmdParse[1];
			
			return cmdParse;
			
		}else if(command.contains(" >") && !command.contains("|")) {
			String [] cmdParse = command.split("\\>");
			String test [] = {command};
			
			for(int i = 0; i <cmdParse.length; i++) {
				//System.out.println(i + " " + cmdParse[i]);
			}
			if(cmdParse.length ==1) {
				String x[] = {cmdParse[0], ">"};
				return x;
			}
			cmdParse[1] = ">"+cmdParse[1];
			return cmdParse;
			
			
		}else if(!command.contains(" >") && command.contains("|")) {
			
			String splitCommand [] = command.split("\\|");
			return splitCommand;
			
			
		}else {
			String comMand [] = {command};
			return comMand;
		}
		
	}
	private static boolean canHaveOut(String command) {
		if(command.equals("pwd") ||command.equals("grep") || command.equals("wc") || command.equals("uniq") || command.equals("cat") || command.equals("ls") ) {
			return true;
		}else {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			return false;
		}
		
		
	}
	
	private static boolean canHaveIn(String command) {
		if(command.equals("grep") || command.equals("wc") || command.equals("uniq") || command.equals(">") ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private static boolean MusthaveParam(String command) {
		if(command.equals("grep") || command.equals("cd") || command.equals(">") || command.equals("cat") ||  command.equals("pwd")  
			||  command.equals("uniq") ||  command.equals("ls") || command.equals("wc")) {
			
			return true;
		}else {
			
			return false;
		}
		
		
	}

	
	    
		
}
