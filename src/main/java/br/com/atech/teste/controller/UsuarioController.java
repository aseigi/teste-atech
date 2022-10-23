package br.com.atech.teste.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.teste.dto.usuario.UsuarioEdicaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioInclusaoDTO;
import br.com.atech.teste.dto.usuario.UsuarioVisualizacaoDTO;
import br.com.atech.teste.service.UsuarioService;
import lombok.AllArgsConstructor;

/**
 * Api para controle de usuários
 * @author Seigi
 *
 */
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping("/{id}")
	public UsuarioVisualizacaoDTO recuperarPorId(@PathVariable Long id) {
		return usuarioService.recuperarPorId(id);
	}
	
	@PostMapping
	public UsuarioEdicaoDTO inserir(@RequestBody @Valid UsuarioInclusaoDTO usuarioInclusaoDto) {
		return usuarioService.inserir(usuarioInclusaoDto);
	}

	@PutMapping
	public void atualizar(@RequestBody @Valid UsuarioEdicaoDTO usuarioEdicaoDto) {
		usuarioService.atualizar(usuarioEdicaoDto);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		usuarioService.remover(id);
	}

}
