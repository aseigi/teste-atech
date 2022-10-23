package br.com.atech.teste.dto.usuario;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioVisualizacaoDTO implements Serializable {

	private static final long serialVersionUID = 3525923884405960305L;
	
	private String nome;
	private String email;
	private String nomeUsuario;
	
}
