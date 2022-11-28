package model;

import java.time.LocalDateTime;

class Emprestimo {

	private String LivroCode;
	private String codExemplar;
	private LocalDateTime date;
	private LocalDateTime dateDevolucao;
	private String userCode;

	public Emprestimo(String livro, String codExemplar, LocalDateTime date, String userCode) {
		super();
		LivroCode = livro;
		this.codExemplar = codExemplar;
		this.date = date;
		this.userCode = userCode;
	}

	public String getLivroCode() {
		return LivroCode;
	}

	public void setLivroCode(String livro) {
		LivroCode = livro;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getCodExemplar() {
		return codExemplar;
	}

	public void setCodExemplar(String codExemplar) {
		this.codExemplar = codExemplar;
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
