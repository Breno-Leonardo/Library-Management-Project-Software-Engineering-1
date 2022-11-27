package system;

import java.util.Scanner;

import command.Console;

public class Sistema {

	public static void main(String[] args) {
		Console.getInstance().inicializarComandos();
		String[] partsOfCommand;
		String command = null;
		Scanner scanner = new Scanner(System.in);
		
		while( command!="sai") {
			System.out.println("Insira o comando: ");
			command=scanner.nextLine();
			partsOfCommand = command.split(" ");
			Console.getInstance().executeCommand(partsOfCommand[0], partsOfCommand[1], partsOfCommand[2]);
		}
		
		scanner.close();
		
	 
	}
	
}
