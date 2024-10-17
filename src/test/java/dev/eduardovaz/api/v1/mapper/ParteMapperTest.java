package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.model.Parte;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParteMapperTest {

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ParteMapper parteMapper;

    @Test
    void deveConverterParteDtoParaParte() {
        ParteDto parteDto = new ParteDto();
        parteDto.setNomeCompleto("João da Silva");
        Parte parte = new Parte();
        parte.setNomeCompleto("João da Silva");

        when(mapper.map(parteDto, Parte.class)).thenReturn(parte);

        Parte parteConvertida = parteMapper.toParte(parteDto);

        assertEquals(parte, parteConvertida);
    }

    @Test
    void deveConverterParteParaParteResponseDto() {
        Parte parte = new Parte();
        parte.setNomeCompleto("João da Silva");
        ParteResponseDto parteResponseDto = new ParteResponseDto();
        parteResponseDto.setNomeCompleto("João da Silva");

        when(mapper.map(parte, ParteResponseDto.class)).thenReturn(parteResponseDto);

        ParteResponseDto parteResponseDtoConvertido = parteMapper.toParteResponseDto(parte);

        assertEquals(parteResponseDto, parteResponseDtoConvertido);
    }
}