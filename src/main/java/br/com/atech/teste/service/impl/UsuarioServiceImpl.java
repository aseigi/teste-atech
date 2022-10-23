package br.com.atech.teste.service.impl;

import org.springframework.stereotype.Service;

import br.com.atech.teste.dto.usuario.UsuarioEdicaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioInclusaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioVisualizacaoDTO;
import br.com.atech.teste.entity.Usuario;
import br.com.atech.teste.exception.UsuarioNaoEncontradoException;
import br.com.atech.teste.exception.ValidacaoException;
import br.com.atech.teste.mapper.UsuarioMapper;
import br.com.atech.teste.repository.UsuarioRepository;
import br.com.atech.teste.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	
	@Override
	public UsuarioVisualizacaoDTO recuperarPorId(Long id) {
		return usuarioRepository.findById(id)
				.filter(u -> u.getFlagAtivo())
				.map(u -> usuarioMapper.toVisualizacaoDto(u))
				.orElseThrow(UsuarioNaoEncontradoException::new);
	}
	
	@Override
	public UsuarioEdicaoDTO inserir(UsuarioInclusaoDTO usuarioInclusaoDto) {
		usuarioRepository.findByEmailAndNomeUsuario(usuarioInclusaoDto.getEmail(), usuarioInclusaoDto.getNomeUsuario())
		.ifPresent(u -> { throw new ValidacaoException("Nome de usuário e e-mail já existente"); } );
		
		Usuario usuario = usuarioMapper.toEntity(usuarioInclusaoDto);
		usuario.setFlagAtivo(true);
		usuario = usuarioRepository.save(usuario);
		return usuarioMapper.toEdicaoDto(usuario);
	}
	
	@Override
	public void atualizar(UsuarioEdicaoDTO usuarioEdicaoDto) {
		usuarioRepository.findByEmailAndNomeUsuario(usuarioEdicaoDto.getEmail(), usuarioEdicaoDto.getNomeUsuario())
		.filter(u -> !usuarioEdicaoDto.getId().equals(u.getId()))
		.ifPresent(u -> { throw new ValidacaoException("Nome de usuário e e-mail já existente"); });
		
		usuarioRepository.findById(usuarioEdicaoDto.getId())
		.ifPresentOrElse(p -> {
			p.setNome(usuarioEdicaoDto.getNome());
			p.setEmail(usuarioEdicaoDto.getEmail());
			p.setNomeUsuario(usuarioEdicaoDto.getNomeUsuario());
			p.setSenha(usuarioEdicaoDto.getSenha());
			usuarioRepository.save(p);
		},
			() -> { throw new UsuarioNaoEncontradoException(); }
		);
	}
	
	
	/**
	 * Realiza a exclusão lógica do registro
	 */
	@Override
	public void remover(Long id) {
		usuarioRepository.findById(id)
		.ifPresentOrElse(p -> {
			p.setFlagAtivo(Boolean.FALSE);
			usuarioRepository.save(p);
		},
			() -> { throw new UsuarioNaoEncontradoException(); }
		);
	}
	
}
