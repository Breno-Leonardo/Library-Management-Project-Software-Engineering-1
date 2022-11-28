package command;

import model.Facede;

class ComandoDevolver implements Command {

	@Override
	public void execute(String userCode, String bookCode) {
		Facede.getInstance().devolver(userCode, bookCode);
	}

}
