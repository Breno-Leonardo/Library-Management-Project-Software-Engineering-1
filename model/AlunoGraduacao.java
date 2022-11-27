package model;


import java.util.ArrayList;


 class AlunoGraduacao implements IUsuario {
	
private String codigo, nome;
private ArrayList<Emprestimo> livrosEmPosse=new ArrayList<>();
public  ArrayList<String> reservas= new ArrayList<String>();

@Override
public int getTempoMaxEmprestimo() {
	return 3;
}

@Override
public int getLimiteEmprestimos() {
	return 3;
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
public boolean viabilidadeEmprestimo(Livro livro) {
	ArrayList<String> motivos=new ArrayList<String>();
	boolean saida= true;
	String mensagem= "O emprestimo do livro "+ livro.getTitulo()+" para o usuario "+nome+" não foi possivel, pelos motivos: ";
	
	if(!VerificadorEmprestimo.getInstance().isDisponivel(livro)) {
		motivos.add("não há exemplares disponiveis");
	}
	if(!VerificadorEmprestimo.getInstance().isDevedor(this)) {
		motivos.add("usuario esta com um livro atrasado");
	}
	if(!VerificadorEmprestimo.getInstance().hasMaxBooks(this)) {
		motivos.add("usuario esta com o maximo de livros");
	}
	if(!VerificadorEmprestimo.getInstance().checarReservas(this,livro.codigo)) {
		motivos.add("livro ja reservado por outros usuarios");
	}
	if(!VerificadorEmprestimo.getInstance().checarEmprestimoExemplar(this,livro.codigo)) {
		motivos.add("o usuario já possui emprestimo deste livro");
	}
	if(saida) {
		System.out.println("O emprestimo do livro "+ livro.getTitulo()+" para o usuario "+nome+" realizado com sucesso.");
}
	else {
		for (int i = 0; i < motivos.size(); i++) {
			mensagem+=motivos.get(i);
			if(i==motivos.size()-1) {
				mensagem+=" e ";
			}
			else {
				mensagem+=", ";
			}
		}
		System.out.println(mensagem);
	}
	return saida;
}

@Override
public ArrayList<String> getReservas() {
	return reservas;
}




}
