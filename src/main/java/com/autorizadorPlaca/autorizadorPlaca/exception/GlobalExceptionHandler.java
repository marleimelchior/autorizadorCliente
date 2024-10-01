package com.autorizadorPlaca.autorizadorPlaca.exception;

import com.autorizadorPlaca.autorizadorPlaca.dto.AuthResDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura exceções de validação de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthResDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Dados inválidos ou pendentes de envio: ");

        // Para cada erro de validação, adicione o campo faltante ou inválido na mensagem
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );

        AuthResDTO errorResponse = new AuthResDTO(errorMessage.toString(), "400");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Captura outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResDTO> handleGeneralExceptions(Exception e) {
        AuthResDTO errorResponse = new AuthResDTO("Erro", "Erro interno");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


