package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

 class VerificadorEmprestimo {
	 private static VerificadorEmprestimo instance;
	 private VerificadorEmprestimo() {}
	 	public static synchronized VerificadorEmprestimo getInstance() {
	 		if (instance == null)
				instance = new VerificadorEmprestimo();
			return instance;
	 	}
	 public boolean isDisponivel(Livro livro) {

			
			if(livro.exemplares.size()>livro.exemplaresEmprestados.size()) {
					return true;
			}
			return false;
	}
	 
	 public boolean checarReservas(IUsuario usuario,String bookCode) {
		 int quantidadeReservas=Biblioteca.getInstance().getReservasAtuais().get(bookCode).size();
		 int exemplaresDisponiveis= Biblioteca.getInstance().getListaDeLivros().get(bookCode).getExemplaresDisponiveis();
		 if(usuario.getReservas().contains(bookCode)) {//ja tem reserva
			 if(exemplaresDisponiveis>=quantidadeReservas)
				 return true;
		 }
		 else {//nao tem reserva
			 if(exemplaresDisponiveis>quantidadeReservas)
				 return true;
		 }
		 
		 return false;
	}
	 
	public boolean isDevedor(IUsuario usuario) {

		for(Emprestimo emprestimo: usuario.getLivrosEmPosse()) {
			if(getDiffTime(emprestimo.getDate())> usuario.getTempoMaxEmprestimo())
				return true;
		}
		return false;
	}

	
	public boolean hasMaxBooks(IUsuario usuario) {
		if((usuario.getLivrosEmPosse().size()>=usuario.getLimiteEmprestimos()) && (usuario.getLimiteEmprestimos()!=-1) )
			return true;
		return false;
	}
	
	public boolean checarEmprestimoExemplar(IUsuario usuario,String bookCode) {
		for(Emprestimo emprestimo: usuario.getLivrosEmPosse()) {
			if(emprestimo.getLivroCode().equals(bookCode))
				return false;
		}
		return true;
	}

	long getDiffTime(LocalDateTime date) {
		return ChronoUnit.DAYS.between(date, LocalDateTime.now());
	}
}
