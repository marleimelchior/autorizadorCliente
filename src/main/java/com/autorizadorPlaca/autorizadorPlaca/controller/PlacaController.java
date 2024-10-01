package com.autorizadorPlaca.autorizadorPlaca.controller;

import com.autorizadorPlaca.autorizadorPlaca.dto.AuthReqDTO;
import com.autorizadorPlaca.autorizadorPlaca.dto.AuthResDTO;
import com.autorizadorPlaca.autorizadorPlaca.service.PlacaService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/placas")
public class PlacaController {
    @Autowired
    private PlacaService placaService;

    private static final Logger logger = LoggerFactory.getLogger(PlacaController.class);

    @PostMapping("/autorizador")
    public ResponseEntity<AuthResDTO> validatePlaca(@Valid @RequestBody AuthReqDTO authReqDTO) {
        logger.info("Recebendo requisição para autorizar placa: {}", authReqDTO.getPlaca());
        AuthResDTO response = placaService.autorizarPlaca(authReqDTO);
        return ResponseEntity.ok(response);
    }
}

