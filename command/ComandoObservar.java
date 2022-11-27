package command;

import model.Facede;

 class ComandoObservar implements Command {

	@Override
	public void execute( String userCode, String bookCode) {
		Facede.getInstance().observar(userCode, bookCode);
	}
}
