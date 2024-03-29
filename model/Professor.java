package model;

import java.util.ArrayList;
import java.util.HashMap;

class Professor implements IUsuarioObserver {

	private String codigo, nome;
	private ArrayList<Emprestimo> livrosEmPosse = new ArrayList<>();
	private ArrayList<Emprestimo> historicoEmprestimos = new ArrayList<>();

	public ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private HashMap<Livro, Integer> notificacoes = new HashMap<Livro, Integer>();// book code and book

	
	public Professor(String codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public HashMap<Livro, Integer> getNotificacoes() {
		return notificacoes;
	}

	@Override
	public int getTempoMaxEmprestimo() {
		return 7;
	}

	@Override
	public int getLimiteEmprestimos() {
		return -1;// representa que nao tem limite
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public ArrayList<Emprestimo> getLivrosEmPosse() {
		return livrosEmPosse;
	}

	@Override
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	@Override
	public boolean viabilidadeEmprestimo(Livro livro) {
		ArrayList<String> motivos = new ArrayList<String>();
		boolean saida = true;
		String mensagem = "O emprestimo do livro " + livro.getTitulo() + " para o usuario " + nome
				+ " n�o foi possivel, pelos motivos: ";

		if (!VerificadorEmprestimo.getInstance().isDisponivel(livro)) {
			saida = false;
			motivos.add("n�o h� exemplares disponiveis");
		}
		if (VerificadorEmprestimo.getInstance().isDevedor(this)== true) {
			saida = false;
			motivos.add("usuario esta com um livro atrasado");
		}

		if (saida) {
			System.out.println("O emprestimo do livro " + livro.getTitulo() + " para o usuario " + nome
					+ " realizado com sucesso.");
		} else {
			for (int i = 0; i < motivos.size(); i++) {
				mensagem += motivos.get(i);
				if (i == motivos.size() - 2) {
					mensagem += " e ";
				} else if(i != motivos.size() - 1) {
					mensagem += ", ";
				}
			}
			System.out.println(mensagem);
		}
		return saida;
	}

	@Override
	public void update(Livro livro) {
		//System.out.println("Fui notificado");
		if (notificacoes.get(livro) == null)
			notificacoes.put(livro, 1);
		else
			notificacoes.put(livro, notificacoes.get(livro) + 1);
	}

	@Override
	public ArrayList<Emprestimo> getLivrosHistoricoEmprestimos() {
		return historicoEmprestimos;
	}

}
