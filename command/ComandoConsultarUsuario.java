package command;

import model.Facede;

class ComandoConsultarUsuario implements Command {

	

	@Override
	public void execute(String userCode, String codeNull) {
		Facede.getInstance().consultarUsuario( userCode);

	}

	

}
