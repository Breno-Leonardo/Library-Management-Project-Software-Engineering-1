package command;

import model.Facede;

class ComandoConsultarLivro implements Command {

	@Override
	public void execute(String bookCode, String codeNull) {
		Facede.getInstance().consultarLivro(bookCode);

	}

}
