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
			Exemplar exemplar = listaDeLivros.get(bookCode).getExemplaresDisponiveis().remove(0);
			Emprestimo emprestimo = new Emprestimo(bookCode, exemplar, LocalDateTime.now(), userCode);
			listaDeLivros.get(bookCode).getExemplaresEmprestados().put(exemplar, emprestimo);
			listaDeUsuarios.get(userCode).getLivrosEmPosse().add(emprestimo);
			emprestimosAtuais.put(bookCode, emprestimo);
		}
	}

	public void devolver(String userCode, String bookCode) {
		for (int i = 0; i < listaDeUsuarios.get(userCode).getLivrosEmPosse().size(); i++) {
			if (listaDeUsuarios.get(userCode).getLivrosEmPosse().get(i).getLivroCode().equals(bookCode)) {
				Emprestimo emprestimo = listaDeUsuarios.get(userCode).getLivrosEmPosse().remove(i);
				listaDeLivros.get(bookCode).getExemplaresDisponiveis().add(emprestimo.getExemplar());
				listaDeLivros.get(bookCode).getExemplaresEmprestados().remove(emprestimo.getExemplar());
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
		
		for (Reserva reserva : listaDeUsuarios.get(userCode).getReservas()) {
			if (reserva.getCodigoLivro().equals(bookCode)) {
				mensagem += " o usuario já possui reserva para este livro";
				podeReservar = false;
				break;
			}
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
			listaDeUsuarios.get(userCode).getReservas().add(new Reserva(bookCode));
			if (listaDeLivros.get(bookCode).getReservas().size() > 2) {
				listaDeLivros.get(bookCode).notifyObservers();
			}
		} else
			System.out.println(mensagem);

	}

	public void observar(String userCode, String bookCode) {
		listaDeLivros.get(bookCode).registerObserver((IUsuarioObserver) listaDeUsuarios.get(userCode));
		System.out.println("Usuario:  " + listaDeUsuarios.get(userCode).getNome()
				+ " foi registrado nas notificacoes do livro: " + listaDeLivros.get(bookCode).getTitulo());
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
		for (Exemplar exemplar : livro.getExemplaresDisponiveis()) {
			System.out.println("Exemplar: " + exemplar.getCodigo() + " Status: Disponivel");
		}
		for (Exemplar exemplarCode : livro.getExemplaresEmprestados().keySet()) {
			Emprestimo emprestimo = livro.getExemplaresEmprestados().get(exemplarCode);
			LocalDateTime dataDevolucao = emprestimo.getDate()
					.plusDays(listaDeUsuarios.get(emprestimo.getUserCode()).getTempoMaxEmprestimo());
			System.out.println("Exemplar: " + exemplarCode + ", Status: Emprestado, Usuario: "
					+ listaDeUsuarios.get(emprestimo.getUserCode()).getNome() + " ,Data: "
					+ emprestimo.getDate().getDayOfMonth() + "/" + emprestimo.getDate().getMonthValue() + "/"
					+ emprestimo.getDate().getYear() + ",Data pevista para devolução: " + dataDevolucao.getDayOfMonth()
					+ "/" + dataDevolucao.getMonthValue() + "/" + dataDevolucao.getYear());
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
					+ "Data Emprestimo: " + emprestimo.getDate().getDayOfMonth() + "/"
					+ emprestimo.getDate().getMonthValue() + "/" + emprestimo.getDate().getYear() + ", Status: Em Curso"
					+ ", Data Devolução Prevista: " + dataDevolucao.getDayOfMonth() + "/"
					+ dataDevolucao.getMonthValue() + "/" + dataDevolucao.getYear());

		}
		if (usuario.getLivrosEmPosse().size() == 0)
			System.out.println("Nenhum");
		System.out.println("Historico Emprestimos:");
		for (Emprestimo emprestimo : usuario.getLivrosHistoricoEmprestimos()) {
			System.out.println("Livro: " + listaDeLivros.get(emprestimo.getLivroCode()).getTitulo()
					+ "Data Emprestimo: " + emprestimo.getDate().getDayOfMonth() + "/"
					+ emprestimo.getDate().getMonthValue() + "/" + emprestimo.getDate().getYear()
					+ ", Status: Finalizado" + ", Data Devolução: " + emprestimo.getDateDevolucao().getDayOfMonth()
					+ "/" + emprestimo.getDateDevolucao().getMonthValue() + "/"
					+ emprestimo.getDateDevolucao().getYear());
		}
		if (usuario.getLivrosHistoricoEmprestimos().size() == 0)
			System.out.println("Nenhum");
	}

	public void consultarProfessor(String userCode) {
		System.out.println("Professor: " + listaDeUsuarios.get(userCode).getNome());
		System.out.println("Notificações:");
		Professor professor = (Professor) listaDeUsuarios.get(userCode);
		for (Livro livro : professor.getNotificacoes().keySet()) {
			System.out.println(
					"Livro: " + livro.getTitulo() + ", Notificações: " + professor.getNotificacoes().get(livro));
		}
		if(professor.getNotificacoes().size()==0)
			System.out.println("Nenhuma");
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

	public void inicializarTeste() {
		listaDeUsuarios.put("123", new AlunoGraduacao("123", "João da Silva"));
		listaDeUsuarios.put("456", new AlunoPosGraduacao("456", "Luiz Fernando Rodrigues"));
		listaDeUsuarios.put("789", new AlunoGraduacao("789", "Pedro Paulo"));
		listaDeUsuarios.put("100", new Professor("100", "Carlos Lucena"));

		listaDeLivros.put("100",
				new Livro("100", "Engenharia de Software", "AddisonWesley", "Ian Sommervile", "6ª", "2000"));
		listaDeLivros.put("101", new Livro("101", "UML – Guia do Usuário", "Campus",
				"Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª ", "2000"));
		listaDeLivros.put("200", new Livro("200", "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", "2014"));
		listaDeLivros.put("201", new Livro("201", "Agile Software Development, Principles, Patterns, and Practices",
				"Prentice Hall", "Robert Martin", "1ª ", "2002"));
		listaDeLivros.put("300", new Livro("300", "Refactoring: Improving the Design of Existing Code",
				"Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"));
		listaDeLivros.put("301", new Livro("301", "Software Metrics: A Rigorous and Practical Approach", "CRC Press",
				"Norman Fenton, James Bieman", "3ª", "2014"));
		listaDeLivros.put("400",
				new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software",
						"Addison-Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
						"1994"));
		listaDeLivros.put("401",
				new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
						"Addison-Wesley Professional", "Martin Fowler", "3ª", "2003"));

		listaDeLivros.get("100").getExemplaresDisponiveis().add(new Exemplar("01"));
		listaDeLivros.get("100").getExemplaresDisponiveis().add(new Exemplar("02"));
		listaDeLivros.get("101").getExemplaresDisponiveis().add(new Exemplar("03"));
		listaDeLivros.get("200").getExemplaresDisponiveis().add(new Exemplar("04"));
		listaDeLivros.get("201").getExemplaresDisponiveis().add(new Exemplar("05"));
		listaDeLivros.get("300").getExemplaresDisponiveis().add(new Exemplar("06"));
		listaDeLivros.get("300").getExemplaresDisponiveis().add(new Exemplar("07"));
		listaDeLivros.get("400").getExemplaresDisponiveis().add(new Exemplar("08"));
		listaDeLivros.get("400").getExemplaresDisponiveis().add(new Exemplar("09"));
	}

}
