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
 		commands.put("obs",new ComandoObservar());
 		commands.put("liv",new ComandoConsultarLivro());
 		commands.put("usu",new ComandoConsultarUsuario());
 		commands.put("ntf",new ComandoConsultarProfessor());
 	}
 	public void executeCommand(String command, String code1, String code2) {
 		Command c= commands.get(command);
 		c.execute(code1, code2);
 	}
 
}
