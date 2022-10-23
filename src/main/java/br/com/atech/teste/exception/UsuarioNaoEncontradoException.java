package br.com.atech.teste.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 2600050232128229501L;

	public UsuarioNaoEncontradoException() {
		super("Usuário não encontrado!");
	}
	  
}
