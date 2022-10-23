package br.com.atech.teste.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.atech.teste.dto.ErroDTO;

@ControllerAdvice
public class ErrorHandlingController {
	
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {	
        return new ResponseEntity<Object>(
        		new ErroDTO("Erros de validação", ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList())),
        		HttpStatus.BAD_REQUEST
        );
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDTO> handleRuntime(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<ErroDTO>(
        		new ErroDTO("Erro no processamento do request", Arrays.asList(ex.getMessage())),
        		HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}