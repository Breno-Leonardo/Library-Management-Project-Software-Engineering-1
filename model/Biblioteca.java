package model;

import java.util.HashMap;
import java.util.LinkedHashMap;

 class Biblioteca {
	private static Biblioteca instance;
	private  HashMap<String,Livro> listaDeLivros= new HashMap<String,Livro>();//  book code and book
	private  HashMap<String,IUsuario> listaDeUsuarios= new HashMap<String,IUsuario>(); // user code and user
	private  HashMap<String,Emprestimo> emprestimosAtuais= new HashMap<String,Emprestimo>();// book code and emprestimo
	private  HashMap<String,LinkedHashMap<String,String>> reservasAtuais= new HashMap<String,LinkedHashMap<String,String>>();// book code and emprestimo
	
	private Biblioteca() {}
 	public static synchronized Biblioteca getInstance() {
 		if (instance == null)
			instance = new Biblioteca();
		return instance;
 	}
 	
 	public void emprestar(String userCode, String bookCode) {
 		IUsuario usuario=listaDeUsuarios.get(userCode);
 		Livro livro= listaDeLivros.get(bookCode);
 		boolean podeEmprestar=usuario.viabilidadeEmprestimo(livro);
 	}
 	public void devolver(String userCode, String bookCode) {
 		
 	}
 	public void reservar(String userCode, String bookCode) {
 		
 	}
 	public void observar(String userCode, String bookCode) {
 		
 	}
 	
 	
 	
	public  HashMap<String, Livro> getListaDeLivros() {
		return listaDeLivros;
	}
	public  HashMap<String, IUsuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}
	public  HashMap<String, Emprestimo> getEmprestimosAtuais() {
		return emprestimosAtuais;
	}
	public HashMap<String, LinkedHashMap<String, String>> getReservasAtuais() {
		return reservasAtuais;
	}
	
 	
 	
 	
 	
 	
 	
 	
	
}
