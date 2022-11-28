package command;

import model.Facede;

class ComandoConsultarProfessor implements Command {

	

	@Override
	public void execute(String userCode, String codeNull) {
		Facede.getInstance().consultarProfessor( userCode);

	}

	

}
