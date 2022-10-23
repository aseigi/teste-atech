package br.com.atech.teste.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO implements Serializable {

	private static final long serialVersionUID = 3972150829432342876L;
	private String mensagem;
    private List<String> detalhes;
    
}