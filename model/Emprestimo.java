package model;

import java.time.LocalDateTime;

class Emprestimo {

	private String livroCode;
	private Exemplar exemplar;
	private LocalDateTime date;
	private LocalDateTime dateDevolucao;
	private String userCode;

	public Emprestimo(String livro, Exemplar exemplar, LocalDateTime date, String userCode) {
		super();
		livroCode = livro;
		this.exemplar = exemplar;
		this.date = date;
		this.userCode = userCode;
	}

	public String getLivroCode() {
		return livroCode;
	}

	public void setLivroCode(String livro) {
		livroCode = livro;
	}

	public String getUserCode() {
		return userCode;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getDateDevolucao() {
		return dateDevolucao;
	}

	public void setDateDevolucao(LocalDateTime dateDevolucao) {
		this.dateDevolucao = dateDevolucao;
	}
}
