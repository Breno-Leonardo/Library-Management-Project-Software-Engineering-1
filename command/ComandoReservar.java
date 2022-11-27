package command;

import model.Facede;

class ComandoReservar implements Command {

	@Override
	public void execute( String userCode, String bookCode) {
		Facede.getInstance().reservar(userCode, bookCode);
	}

}
