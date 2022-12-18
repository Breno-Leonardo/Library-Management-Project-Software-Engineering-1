package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class Livro implements Subject {
	private String codigo, titulo, editora, autores, edicao, anoPublicacao;

	public Livro(String codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autores = autores;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
	}

	private ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
	private LinkedHashMap<Exemplar, Emprestimo> exemplaresEmprestados = new LinkedHashMap<Exemplar, Emprestimo>();
	private ArrayList<String> reservas = new ArrayList<String>();
	private ArrayList<IUsuarioObserver> observers = new ArrayList<IUsuarioObserver>();

	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutores() {
		return autores;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public ArrayList<Exemplar> getExemplaresDisponiveis() {
		return exemplares;
	}

	public LinkedHashMap<Exemplar, Emprestimo> getExemplaresEmprestados() {
		return exemplaresEmprestados;
	}

	public ArrayList<String> getReservas() {
		return reservas;
	}

	public void registerObserver(IUsuarioObserver o) {
		observers.add(o);
	}

	public void removeObserver(IUsuarioObserver o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			IUsuarioObserver observer = observers.get(i);
			observer.update(this);
		}
	}

	public ArrayList<IUsuarioObserver> getObservers() {
		return observers;
	}

}
