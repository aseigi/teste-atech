package br.com.atech.teste.dto.usuario;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsuarioEdicaoDTO extends UsuarioInclusaoDTO implements Serializable {

	private static final long serialVersionUID = -6259463978797044457L;
	
	private Long id;
	
    @Builder(builderMethodName = "edicaoBuilder")
    public UsuarioEdicaoDTO(final Long id, final String nome, final String email, final String nomeUsuario, final String senha) {
        super(nome, email, nomeUsuario, senha);
        this.id = id;
    }
	
}