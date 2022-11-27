package command;

import java.util.HashMap;

 public class Console {
	private static Console instance;
	private Console() {}
	public static HashMap<String,Command> commands= new HashMap<String,Command>();
 	
	public static synchronized Console getInstance() {
 		if (instance == null) {
			instance = new Console();
			}
		return instance;
 	}
 	
 	public void inicializarComandos() {
 		commands.put("emp",new ComandoEmprestimo());
 		commands.put("res",new ComandoReservar());
 		commands.put("dev",new ComandoDevolver());
 		commands.put("obs",new ComandoDevolver());
 	}
 	public void executeCommand(String command, String userCode, String bookCode) {
 		Command c= commands.get(command);
 		c.execute(userCode, bookCode);
 	}
 
}
