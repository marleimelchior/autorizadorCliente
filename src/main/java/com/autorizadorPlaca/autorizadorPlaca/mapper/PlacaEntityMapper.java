package com.autorizadorPlaca.autorizadorPlaca.mapper;

import com.autorizadorPlaca.autorizadorPlaca.dto.AuthReqDTO;
import com.autorizadorPlaca.autorizadorPlaca.dto.AuthResDTO;
import com.autorizadorPlaca.autorizadorPlaca.entity.ParceiroEntity;
import com.autorizadorPlaca.autorizadorPlaca.entity.PlacaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlacaEntityMapper {

    AuthReqDTO toDto(PlacaEntity placaEntity);

    PlacaEntity toEntity(AuthReqDTO authReqDTO);

    @Mapping(source = "placaEntity.parceiro.dsNome", target = "parceiro")
    @Mapping(target = "status", ignore = true)
    AuthResDTO toResponseDto(PlacaEntity placaEntity);

    default AuthResDTO toResponseDto(PlacaEntity placaEntity, String status) {
        AuthResDTO responseDto = toResponseDto(placaEntity);
        responseDto.setStatus(status);
        return responseDto;
    }
}

