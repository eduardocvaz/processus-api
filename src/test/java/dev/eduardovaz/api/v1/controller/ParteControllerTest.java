package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.service.ParteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParteControllerTest {

    @Mock
    private ParteService parteService;

    @InjectMocks
    private ParteController parteController;

    @Test
    void deveCriarParte() {
        ParteDto parteDto = new ParteDto();
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteService.save(parteDto)).thenReturn(parteResponseDto);

        ResponseEntity<ParteResponseDto> response = parteController.criar(parteDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(parteResponseDto, response.getBody());
    }

    @Test
    void deveEditarParte() {
        ParteDto parteDto = new ParteDto();
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteService.editar(parteDto)).thenReturn(parteResponseDto);

        ResponseEntity<ParteResponseDto> response = parteController.editar(parteDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parteResponseDto, response.getBody());
    }

    @Test
    void deveConsultarPartePorId() {
        Long parteId = 1L;
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteService.obterParte(parteId)).thenReturn(parteResponseDto);

        ResponseEntity<ParteResponseDto> response = parteController.consultarPorId(parteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parteResponseDto, response.getBody());
    }

    @Test
    void deveConsultarPartes() {
        List<ParteResponseDto> parteResponseDtos = List.of(new ParteResponseDto(), new ParteResponseDto());

        when(parteService.listarPartes()).thenReturn(parteResponseDtos);

        ResponseEntity<List<ParteResponseDto>> response = parteController.consultar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parteResponseDtos, response.getBody());
    }

    @Test
    void deveAssociarPartesAoProcesso() {
        Long processoId = 1L;
        List<Long> partesIds = List.of(1L, 2L);

        ResponseEntity<Void> response = parteController.associarPartesAoProcesso(processoId, partesIds);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deveExcluirParte() {
        Long parteId = 1L;

        ResponseEntity<Void> response = parteController.delete(parteId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}