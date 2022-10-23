package br.com.atech.teste;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.atech.teste.dto.usuario.UsuarioEdicaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioInclusaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioVisualizacaoDTO;
import br.com.atech.teste.entity.Usuario;
import br.com.atech.teste.exception.UsuarioNaoEncontradoException;
import br.com.atech.teste.exception.ValidacaoException;
import br.com.atech.teste.mapper.UsuarioMapper;
import br.com.atech.teste.repository.UsuarioRepository;
import br.com.atech.teste.service.impl.UsuarioServiceImpl;

@SpringBootTest
class UsuarioServiceTest {

	@InjectMocks
	UsuarioServiceImpl usuarioService;
	
	@Mock
	UsuarioRepository usuarioRepository;

	@Mock
	UsuarioMapper usuarioMapper;
	
	
	@Test
	public void recuperarUsuarioNaoExistenteTest() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.recuperarPorId(1L));
	}
	
	@Test
	public void recuperarUsuarioExistenteAtivoTest() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(Usuario.builder().flagAtivo(true).build()));
		when(usuarioMapper.toVisualizacaoDto(any())).thenReturn(UsuarioVisualizacaoDTO.builder().build());
		assertNotNull(usuarioService.recuperarPorId(1L));
	}
	
	@Test
	public void recuperarUsuarioExistenteNaoAtivoTest() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(Usuario.builder().flagAtivo(false).build()));
		Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.recuperarPorId(1L));
	}
	
	@Test
	public void inserirUsuarioValidacaoTest() {
		when(usuarioRepository.findByEmailAndNomeUsuario(nullable(String.class), nullable(String.class))).thenReturn(Optional.of(Usuario.builder().build()));
		Assertions.assertThrows(ValidacaoException.class, () -> usuarioService.inserir(UsuarioEdicaoDTO.builder().build()));
	}
	
	@Test
	public void inserirUsuarioSucessoTest() {
		UsuarioInclusaoDTO dto = UsuarioInclusaoDTO.builder().build();
		
		when(usuarioRepository.findByEmailAndNomeUsuario(nullable(String.class), nullable(String.class))).thenReturn(Optional.empty());	
		when(usuarioMapper.toEntity(dto)).thenReturn(Usuario.builder().build());
		usuarioService.inserir(dto);
		verify(usuarioRepository, times(1)).save(any());
	}
	
	@Test
	public void atualizarUsuarioValidacaoTest() {
		when(usuarioRepository.findByEmailAndNomeUsuario(nullable(String.class), nullable(String.class))).thenReturn(Optional.of(Usuario.builder().id(2L).build()));
		Assertions.assertThrows(ValidacaoException.class, () -> usuarioService.atualizar(UsuarioEdicaoDTO.edicaoBuilder().id(1L).build()));
	}
	
	@Test
	public void atualizarUsuarioExistenteTest() {
		when(usuarioRepository.findByEmailAndNomeUsuario(nullable(String.class), nullable(String.class))).thenReturn(Optional.empty());
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(Usuario.builder().build()));
		usuarioService.atualizar(UsuarioEdicaoDTO.edicaoBuilder().id(1L).build());
		verify(usuarioRepository, times(1)).save(any());
	}
	
	@Test
	public void atualizarUsuarioNaoExistenteTest() {
		when(usuarioRepository.findByEmailAndNomeUsuario(nullable(String.class), nullable(String.class))).thenReturn(Optional.empty());
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.atualizar(UsuarioEdicaoDTO.edicaoBuilder().id(1L).build()));
	}
	
	@Test
	public void removerUsuarioExistenteTest() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(Usuario.builder().build()));
		usuarioService.remover(1L);
		verify(usuarioRepository, times(1)).save(any());
	}
	
	@Test
	public void removerUsuarioNaoExistenteTest() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.remover(1L));
	}
}
