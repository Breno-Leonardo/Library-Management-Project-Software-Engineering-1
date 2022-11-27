package command;

import model.Facede;

 class ComandoEmprestimo implements Command {

	@Override
	public void execute( String userCode, String bookCode) {
		Facede.getInstance().emprestar(userCode, bookCode);
	}

}
