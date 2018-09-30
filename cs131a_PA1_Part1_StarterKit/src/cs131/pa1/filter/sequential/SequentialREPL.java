package cs131.pa1.filter.sequential;
import java.io.File;
import java.util.*;

import com.sun.xml.internal.ws.util.StringUtils;

import cs131.pa1.commands.Cat;
import cs131.pa1.commands.ChangeDirectory;
import cs131.pa1.commands.Grep;
import cs131.pa1.commands.ListDirectory;
import cs131.pa1.commands.PWD;
import cs131.pa1.commands.Wc;
import cs131.pa1.commands.uniq;
import cs131.pa1.filter.Message;

public class SequentialREPL {
	

	static String currentWorkingDirectory = System.getProperty("user.dir").toString();
	public static String OGDir = System.getProperty("user.dir").toString();
	public StringBuilder  entirePath = new StringBuilder();
	public static Queue<String> path = new LinkedList<String>();
	public static boolean isFile = false;

	
	
	
	
	public static void main(String[] args){
		
		System.out.print(Message.WELCOME);
		UnixShell();
		System.out.print(Message.GOODBYE);
		
	}

	private static void UnixShell() {
		SequentialCommandBuilder cmdBuilder = new SequentialCommandBuilder();
		Scanner input = new Scanner(System.in);
		String command = " ";
		
		while(!command.equals("exit")) {
			System.out.print(Message.NEWCOMMAND);
			command = input.nextLine();

			if(!command.equals("exit")) {
				SequentialCommandBuilder commandBuild = new SequentialCommandBuilder();
				List<SequentialFilter> filterPath = commandBuild.createFiltersFromCommand(command);
				execute(filterPath);
				
				}
				
			}
		
	}

	public static String getCurrentDir() {
		return currentWorkingDirectory;
	}
	
	public static String changeCurrDirect(String filePath) {
		return currentWorkingDirectory = filePath;
	}

	

	private static void execute(List<SequentialFilter> commandList) {
		
		System.out.println(commandList.toString());
		
		for(int i = 0; i < commandList.size();i++) {
			commandList.get(i).process();
		}
		
		
	}

	
	

	
}


		
