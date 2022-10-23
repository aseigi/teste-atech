package br.com.atech.teste.service;

import br.com.atech.teste.dto.usuario.UsuarioEdicaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioInclusaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioVisualizacaoDTO;

public interface UsuarioService {

	public UsuarioVisualizacaoDTO recuperarPorId(Long id);
	public UsuarioEdicaoDTO inserir(UsuarioInclusaoDTO usuarioInclusaoDTO);
	public void atualizar(UsuarioEdicaoDTO usuarioEdicaoDTO);
	public void remover(Long id);
	
}
