package model;

import java.util.ArrayList;


 class Livro {
	String codigo, titulo, editora, autores, edicao, anoPublicacao;
	
	public Livro(String codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autores = autores;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
	}
	public  ArrayList<String> exemplares= new ArrayList<String>();
	public  ArrayList<String> exemplaresEmprestados= new ArrayList<String>();
	public  ArrayList<String> reservas= new ArrayList<String>();
	
	public int getExemplaresDisponiveis() {
		return exemplares.size()-exemplaresEmprestados.size();
	}

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

	public ArrayList<String> getExemplares() {
		return exemplares;
	}

	public ArrayList<String> getExemplaresEmprestados() {
		return exemplaresEmprestados;
	}

	public ArrayList<String> getReservas() {
		return reservas;
	}
	
	
}
