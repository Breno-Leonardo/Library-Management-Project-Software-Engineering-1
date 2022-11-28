package model;

import java.util.ArrayList;

 interface IUsuario {
public int getTempoMaxEmprestimo();
public int getLimiteEmprestimos();
public String getCodigo();
public String getNome();
public ArrayList<Emprestimo> getLivrosEmPosse() ;
public ArrayList<Emprestimo> getLivrosHistoricoEmprestimos() ;
public ArrayList<String> getReservas() ;
public boolean viabilidadeEmprestimo(Livro livro);

}
