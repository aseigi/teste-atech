package br.com.atech.teste.mapper;

import org.mapstruct.Mapper;

import br.com.atech.teste.dto.usuario.UsuarioEdicaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioInclusaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioVisualizacaoDTO;
import br.com.atech.teste.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	UsuarioEdicaoDTO toEdicaoDto(Usuario usuario);
	UsuarioVisualizacaoDTO toVisualizacaoDto(Usuario usuario);
	Usuario toEntity(UsuarioInclusaoDTO usuarioDto);
	Usuario toEntity(UsuarioEdicaoDTO usuarioDto);
	
}
