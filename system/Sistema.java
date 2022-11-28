package system;

import java.util.Scanner;

import command.Console;
import model.Facede;

public class Sistema {

	public static void main(String[] args) {
		Console.getInstance().inicializarComandos();
		Facede.getInstance().inicializarTeste();
		String[] partsOfCommand;
		String command = null;
		Scanner scanner = new Scanner(System.in);

		while (command != "sai") {
			System.out.println("Insira o comando: ");
			command = scanner.nextLine();
			partsOfCommand = command.split(" ");
			if (partsOfCommand.length>2)
				Console.getInstance().executeCommand(partsOfCommand[0], partsOfCommand[1], partsOfCommand[2]);
			else
				Console.getInstance().executeCommand(partsOfCommand[0], partsOfCommand[1],null);

		}

		scanner.close();

	}

}
