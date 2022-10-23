package br.com.atech.teste.dto.usuario;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioInclusaoDTO implements Serializable {

	private static final long serialVersionUID = -173062839548112416L;
	
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "email é obrigatório")
	private String email;
	
	@NotBlank(message = "nomeUsuario é obrigatório")
	private String nomeUsuario;
	
	@NotBlank(message = "senha é obrigatório")
	@Pattern(regexp = "^.{6,}$", message = "senha deve conter 6 caracteres no mínimo")
	private String senha;
	
}
