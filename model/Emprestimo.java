package model;

import java.time.LocalDateTime;

 class Emprestimo {

	private String LivroCode;
	private String codExemplar;
	private LocalDateTime date;
	public Emprestimo(String livro, String codExemplar, LocalDateTime date) {
		super();
		LivroCode = livro;
		this.codExemplar = codExemplar;
		this.date = date;
	}
	public String getLivroCode() {
		return LivroCode;
	}
	public void setLivroCode(String livro) {
		LivroCode = livro;
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
	
}
