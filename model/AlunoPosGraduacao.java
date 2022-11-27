package model;
import java.util.ArrayList;

 class AlunoPosGraduacao implements IUsuario{

	private String codigo, nome;
	private ArrayList<Emprestimo> livrosEmPosse=new ArrayList<>();

	@Override
	public int getTempoMaxEmprestimo() {
		return 4;
	}

	@Override
	public int getLimiteEmprestimos() {
		return 4;
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
