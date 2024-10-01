package com.autorizadorPlaca.autorizadorPlaca.service;

import com.autorizadorPlaca.autorizadorPlaca.dto.AuthReqDTO;
import com.autorizadorPlaca.autorizadorPlaca.dto.AuthResDTO;
import com.autorizadorPlaca.autorizadorPlaca.entity.ConcessionariaEntity;
import com.autorizadorPlaca.autorizadorPlaca.entity.PlacaEntity;
import com.autorizadorPlaca.autorizadorPlaca.repository.PlacaConcessionPracaBloqueRepository;
import com.autorizadorPlaca.autorizadorPlaca.repository.PlacaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacaService {

    private static final Logger logger = LoggerFactory.getLogger(PlacaService.class);

    @Autowired
    private PlacaRepository placaRepository;

    @Autowired
    private PlacaConcessionPracaBloqueRepository placaConcessionPracaBloqueRepository;


    public AuthResDTO autorizarPlaca(AuthReqDTO authReqDTO) {
        try {
            logger.info("Iniciando a autorização da placa: {}", authReqDTO.getPlaca());

            List<PlacaEntity> placas = buscarPlacas(authReqDTO);
            if (placas.isEmpty()) {
                //TODO: lançar exceção caso não encontre a placa
                return placaNaoEncontrada(authReqDTO);
            }

            PlacaEntity placa = placas.get(0);
            String status = verificarStatusPlaca(placa, authReqDTO);

            logger.info("Finalizando autorização da placa: {}, status: {}", authReqDTO.getPlaca(), status);
            return new AuthResDTO(status, placa.getParceiro().getDsNome());
        } catch (Exception e) {
            logger.error("Erro ao autorizar placa: {}", e.getMessage(), e);
            return new AuthResDTO("Erro ao processar a requisição", "Erro interno");
        }
    }


    private List<PlacaEntity> buscarPlacas(AuthReqDTO authReqDTO) {
        logger.info("Consultando placas para: {} e concessionária: {}", authReqDTO.getPlaca(), authReqDTO.getConcessionaria());
        return placaRepository.findByDsPlacaAndConcessionarias_CoConcessionaria(
                authReqDTO.getPlaca(), authReqDTO.getConcessionaria());
    }

    private AuthResDTO placaNaoEncontrada(AuthReqDTO authReqDTO) {
        logger.info("Nenhuma placa encontrada para: {} e concessionária: {}", authReqDTO.getPlaca(), authReqDTO.getConcessionaria());
        return new AuthResDTO("Nenhuma Placa Encontrada na consulta", "Não informado");
    }

    private String verificarStatusPlaca(PlacaEntity placa, AuthReqDTO authReqDTO) {
        String status = "autorizado";

        logger.info("Consultando placa: {}", placa.getDsPlaca());
        if ("N".equals(placa.getStAtivo())) {
            logger.info("Placa inativa: {}", placa.getDsPlaca());
            status = "não autorizado";
        } else {
            Long idPlacaConcessionaria = buscarIdPlacaConcessionaria(placa, authReqDTO);
            if (idPlacaConcessionaria == null) {
                logger.info("Nenhuma idPlacaConcessionaria encontrado para a placa: {} e concessionária: {}", placa.getDsPlaca(), authReqDTO.getConcessionaria());
                return "não autorizado";
            }

            boolean isBlocked = verificarBloqueioPraca(idPlacaConcessionaria, authReqDTO.getPraca());
            if (isBlocked) {
                status = "não autorizado";
            }
        }
        logger.info("Parceiro da placa: {}", placa.getParceiro().getDsNome());
        logger.info("Resultado da autorização: {}", status);

        return status;
    }

    private Long buscarIdPlacaConcessionaria(PlacaEntity placa, AuthReqDTO authReqDTO) {
        return placa.getConcessionarias().stream()
                .filter(c -> c.getCoConcessionaria().equals(authReqDTO.getConcessionaria()))
                .findFirst()
                .map(ConcessionariaEntity::getIdPlacaConcessionaria)
                .orElse(null);
    }

    private boolean verificarBloqueioPraca(Long idPlacaConcessionaria, Long codigoPraca) {
        return placaConcessionPracaBloqueRepository.existsByIdPlacaConcessionariaAndCodigoPraca(
                idPlacaConcessionaria, codigoPraca);
    }


}