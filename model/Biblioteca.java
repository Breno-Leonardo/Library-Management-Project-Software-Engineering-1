package model;

import java.time.LocalDateTime;
import java.util.HashMap;

class Biblioteca {
	private static Biblioteca instance;
	private HashMap<String, Livro> listaDeLivros = new HashMap<String, Livro>();// book code and book
	private HashMap<String, IUsuario> listaDeUsuarios = new HashMap<String, IUsuario>(); // user code and user
	private HashMap<String, Emprestimo> emprestimosAtuais = new HashMap<String, Emprestimo>();// book code and
																								// emprestimo

	private Biblioteca() {
	}

	public static synchronized Biblioteca getInstance() {
		if (instance == null)
			instance = new Biblioteca();
		return instance;
	}

	public void emprestar(String userCode, String bookCode) {
		boolean podeEmprestar = listaDeUsuarios.get(userCode).viabilidadeEmprestimo(listaDeLivros.get(bookCode));
		if (podeEmprestar) {
			String exemplarCode = listaDeLivros.get(bookCode).getExemplaresDisponiveis().remove(0);
			Emprestimo emprestimo = new Emprestimo(bookCode, exemplarCode, LocalDateTime.now(), userCode);
			listaDeLivros.get(bookCode).getExemplaresEmprestados().put(exemplarCode, emprestimo);
			listaDeUsuarios.get(userCode).getLivrosEmPosse().add(emprestimo);
			emprestimosAtuais.put(bookCode, emprestimo);
		}
	}

	public void devolver(String userCode, String bookCode) {
		for (int i = 0; i < listaDeUsuarios.get(userCode).getLivrosEmPosse().size(); i++) {
			if (listaDeUsuarios.get(userCode).getLivrosEmPosse().get(i).getLivroCode().equals(bookCode)) {
				Emprestimo emprestimo = listaDeUsuarios.get(userCode).getLivrosEmPosse().remove(i);
				listaDeLivros.get(bookCode).getExemplaresDisponiveis().add(emprestimo.getCodExemplar());
				listaDeLivros.get(bookCode).getExemplaresEmprestados().remove(emprestimo.getCodExemplar());
				emprestimosAtuais.remove(emprestimo.getLivroCode(), emprestimo);

				emprestimo.setDateDevolucao(LocalDateTime.now());
				listaDeUsuarios.get(userCode).getLivrosHistoricoEmprestimos().add(emprestimo);

				System.out.println("Devolução do livro: " + listaDeLivros.get(bookCode).getTitulo()
						+ " efetuada com sucesso pelo usuario: " + listaDeUsuarios.get(userCode).getNome());
				return;
			}
		}
		System.out.println("Não foi possivel realizar a devolução, o usuario " + listaDeUsuarios.get(userCode).getNome()
				+ " não possui emprestimo do livro " + listaDeLivros.get(bookCode).getTitulo() + ".");
	}

	public void reservar(String userCode, String bookCode) {
		boolean podeReservar = true;
		String mensagem = "A reserva do livro: " + listaDeLivros.get(bookCode).getTitulo() + " para o usuario "
				+ listaDeUsuarios.get(userCode).getNome() + " não foi possivel, pelos motivos: ";
		if (listaDeUsuarios.get(userCode).getReservas().contains(bookCode) == true) {
			mensagem += " o usuario já possui reserva para este livro";
			podeReservar = false;
		}
		if ((listaDeUsuarios.get(userCode).getReservas().size() == 3)) {
			podeReservar = false;
			if (!podeReservar)
				mensagem += " e já possui o maximo de reservas";
			else
				mensagem += " o usuario já possui o maximo de reservas";
		}
		if (podeReservar) {
			System.out.println("Reserva do livro: " + listaDeLivros.get(bookCode).getTitulo()
					+ " efetuada com sucesso pelo usuario: " + listaDeUsuarios.get(userCode).getNome());
			listaDeLivros.get(bookCode).getReservas().add(userCode);
			listaDeUsuarios.get(userCode).getReservas().add(bookCode);
			if (listaDeLivros.get(bookCode).getReservas().size() > 2) {
				listaDeLivros.get(bookCode).notifyObservers();
			}
		} else
			System.out.println(mensagem);

	}

	public void observar(String userCode, String bookCode) {
		listaDeLivros.get(bookCode).registerObserver((IUsuarioObserver) listaDeUsuarios.get(userCode));
	}

	public void consultarLivro(String bookCode) {
		Livro livro = listaDeLivros.get(bookCode);
		System.out.println("Titulo: " + livro.getTitulo());
		System.out.println("Quantidade de reservas: " + livro.getReservas().size());
		if (livro.getReservas().size() != 0) {
			System.out.println("Usuarios que realizaram reserva:");
			for (String userCode : livro.getReservas()) {
				System.out.println("Usuario: " + listaDeUsuarios.get(userCode).getNome());
			}
		}
		System.out.println("Exemplares: ");
		for (String exemplarCode : livro.getExemplaresDisponiveis()) {
			System.out.println("Exemplar: " + exemplarCode + " Status: Disponivel");
		}
		for (String exemplarCode : livro.getExemplaresEmprestados().keySet()) {
			Emprestimo emprestimo = livro.getExemplaresEmprestados().get(exemplarCode);
			System.out.println("Exemplar: " + exemplarCode + ", Status: Emprestado, Usuario: "
					+ listaDeUsuarios.get(emprestimo.getUserCode()).getNome() + " ,Data: " + emprestimo.getDate()
					+ ",Data pevista para devolução: " + emprestimo.getDate()
							.plusDays(listaDeUsuarios.get(emprestimo.getUserCode()).getTempoMaxEmprestimo()));
		}
	}

	public void consultarUsuario(String userCode) {
		System.out.println("Usuario: " + listaDeUsuarios.get(userCode).getNome());
		System.out.println("Emprestimos Atuais:");
		IUsuario usuario = listaDeUsuarios.get(userCode);
		for (Emprestimo emprestimo : usuario.getLivrosEmPosse()) {
			LocalDateTime dataDevolucao = emprestimo.getDate()
					.plusDays(listaDeUsuarios.get(userCode).getTempoMaxEmprestimo());
			System.out.println("Livro: " + listaDeLivros.get(emprestimo.getLivroCode()).getTitulo()
					+ "Data Emprestimo: " + emprestimo.getDate() + ", Status: Em Curso" + ", Data Devolução Prevista: "
					+ dataDevolucao);

		}
		System.out.println("Historico Emprestimos:");
		for (Emprestimo emprestimo : usuario.getLivrosHistoricoEmprestimos()) {
			System.out.println("Livro: " + listaDeLivros.get(emprestimo.getLivroCode()).getTitulo()
					+ "Data Emprestimo: " + emprestimo.getDate() + ", Status: Finalizado" + ", Data Devolução: "
					+ emprestimo.getDateDevolucao());
		}

	}

	public void consultarProfessor(String userCode) {
		System.out.println("Professor: " + listaDeUsuarios.get(userCode).getNome());
		System.out.println("Notificações:");
		Professor professor = (Professor) listaDeUsuarios.get(userCode);
		for (Livro livro : professor.getNotificacoes().keySet()) {
			System.out.println(
					"Livro: " + livro.getTitulo() + ", Notificações: " + professor.getNotificacoes().get(livro));
		}
	}

	public HashMap<String, Livro> getListaDeLivros() {
		return listaDeLivros;
	}

	public HashMap<String, IUsuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public HashMap<String, Emprestimo> getEmprestimosAtuais() {
		return emprestimosAtuais;
	}

}
