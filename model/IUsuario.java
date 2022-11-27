package model;

import java.util.ArrayList;

public interface IUsuario {
public int getTempoMaxEmprestimo();
public int getLimiteEmprestimos();
public String getCodigo();
public String getNome();
public ArrayList<Emprestimo> getLivrosEmPosse() ;
public ArrayList<String> getReservas() ;
public boolean viabilidadeEmprestimo(Livro livro);

}
