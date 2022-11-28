package model;

public class Facede {
	private static Facede instance;

	private Facede() {
	}

	public static synchronized Facede getInstance() {
		if (instance == null)
			instance = new Facede();
		return instance;
	}

	public void emprestar(String userCode, String bookCode) {
		Biblioteca.getInstance().emprestar(userCode, bookCode);
	}

	public void devolver(String userCode, String bookCode) {
		Biblioteca.getInstance().devolver(userCode, bookCode);
	}

	public void reservar(String userCode, String bookCode) {
		Biblioteca.getInstance().reservar(userCode, bookCode);
	}

	public void observar(String userCode, String bookCode) {
		Biblioteca.getInstance().observar(userCode, bookCode);
	}

	public void consultarLivro(String bookCode) {
		Biblioteca.getInstance().consultarLivro(bookCode);
	}

	public void consultarUsuario(String userCode) {
		Biblioteca.getInstance().consultarUsuario(userCode);
	}

	public void consultarProfessor(String userCode) {
		Biblioteca.getInstance().consultarProfessor(userCode);
	}
	public void inicializarTeste() {
		Biblioteca.getInstance().inicializarTeste();
	}
}
