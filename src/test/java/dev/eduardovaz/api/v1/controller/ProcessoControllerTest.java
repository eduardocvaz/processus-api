package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.AtualizarStatusProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import dev.eduardovaz.api.v1.service.ProcessoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessoControllerTest {

    @Mock
    private ProcessoService processoService;

    @InjectMocks
    private ProcessoController processoController;

    @Test
    void deveCriarProcesso() {
        ProcessoDto processoDto = new ProcessoDto();
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoService.save(processoDto)).thenReturn(processoResponseDto);

        ResponseEntity<ProcessoResponseDto> response = processoController.criar(processoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(processoResponseDto, response.getBody());
    }

    @Test
    void deveEditarProcesso() {
        ProcessoDto processoDto = new ProcessoDto();
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoService.editar(processoDto)).thenReturn(processoResponseDto);

        ResponseEntity<ProcessoResponseDto> response = processoController.editar(processoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processoResponseDto, response.getBody());
    }

    @Test
    void deveAtualizarStatusDoProcesso() {
        Long processoId = 1L;
        AtualizarStatusProcessoDto dto = new AtualizarStatusProcessoDto();

        ResponseEntity<Void> response = processoController.atualizarStatusProcesso(processoId, dto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deveConsultarProcessoPorId() {
        Long processoId = 1L;
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoService.obterProcesso(processoId)).thenReturn(processoResponseDto);

        ResponseEntity<ProcessoResponseDto> response = processoController.consultarPorId(processoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processoResponseDto, response.getBody());
    }

    @Test
    void deveConsultarProcessos() {
        List<ProcessoResponseDto> processoResponseDtos = List.of(new ProcessoResponseDto(), new ProcessoResponseDto());

        when(processoService.listarProcessos()).thenReturn(processoResponseDtos);

        ResponseEntity<List<ProcessoResponseDto>> response = processoController.consultar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processoResponseDtos, response.getBody());
    }

    @Test
    void deveArquivarProcessos() {
        List<Long> ids = List.of(1L, 2L);

        ResponseEntity<Void> response = processoController.arquivar(ids);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deveBuscarProcessos() {
        Long id = 1L;
        StatusProcesso status = StatusProcesso.ATIVO;
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);
        LocalDate dataFim = LocalDate.of(2024, 12, 31);
        String cpfCnpj = "12345678901";
        List<ProcessoResponseDto> processoResponseDtos = List.of(new ProcessoResponseDto(), new ProcessoResponseDto());

        when(processoService.buscarProcessos(id, status, dataInicio, dataFim, cpfCnpj))
                .thenReturn(processoResponseDtos);

        ResponseEntity<List<ProcessoResponseDto>> response = processoController.buscarProcessos(
                id, status, dataInicio, dataFim, cpfCnpj);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processoResponseDtos, response.getBody());
    }
}