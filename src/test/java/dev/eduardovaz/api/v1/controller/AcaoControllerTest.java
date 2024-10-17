package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.service.AcaoService;
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
class AcaoControllerTest {

    @Mock
    private AcaoService acaoService;

    @InjectMocks
    private AcaoController acaoController;

    @Test
    void deveRegistrarAcao() {
        Long processoId = 1L;
        AcaoDto acaoDto = new AcaoDto();
        AcaoResponseDto acaoResponseDto = new AcaoResponseDto();

        when(acaoService.registrarAcao(processoId, acaoDto)).thenReturn(acaoResponseDto);

        ResponseEntity<AcaoResponseDto> response = acaoController.registrarAcao(processoId, acaoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(acaoResponseDto, response.getBody());
    }

    @Test
    void deveListarAcoesPorProcesso() {
        Long processoId = 1L;
        List<AcaoResponseDto> acaoResponseDtos = List.of(new AcaoResponseDto(), new AcaoResponseDto());

        when(acaoService.listarAcoesPorProcesso(processoId)).thenReturn(acaoResponseDtos);

        ResponseEntity<List<AcaoResponseDto>> response = acaoController.listarAcoesPorProcesso(processoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(acaoResponseDtos, response.getBody());
    }

    @Test
    void deveAtualizarAcao() {
        Long acaoId = 1L;
        AcaoDto acaoDto = new AcaoDto();
        AcaoResponseDto acaoResponseDto = new AcaoResponseDto();

        when(acaoService.atualizarAcao(acaoId, acaoDto)).thenReturn(acaoResponseDto);

        ResponseEntity<AcaoResponseDto> response = acaoController.atualizarAcao(acaoId, acaoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(acaoResponseDto, response.getBody());
    }

    @Test
    void deveExcluirAcao() {
        Long acaoId = 1L;

        ResponseEntity<Void> response = acaoController.excluirAcao(acaoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

