package model;

import java.util.ArrayList;



 class Professor implements IUsuario{
	 
	private String codigo, nome;
	private ArrayList<Emprestimo> livrosEmPosse=new ArrayList<>();
	
	@Override
	public int getTempoMaxEmprestimo() {
		return 7;
	}

	@Override
	public int getLimiteEmprestimos() {
		return -1;//representa que nao tem limite
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
	public ArrayList<String> getReservas() {
		return null;
	}

	@Override
	public boolean viabilidadeEmprestimo(Livro livro) {
		return false;
	}

	


}
