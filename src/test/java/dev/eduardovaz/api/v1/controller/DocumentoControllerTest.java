package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.api.v1.service.DocumentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentoControllerTest {

    @Mock
    private DocumentoService documentoService;

    @InjectMocks
    private DocumentoController documentoController;

    @Test
    void deveCriarDocumento() {
        Long acaoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(documentoService.criarDocumento(acaoId, documentoDto)).thenReturn(documentoResponseDto);

        ResponseEntity<DocumentoResponseDto> response = documentoController.criarDocumento(acaoId, documentoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(documentoResponseDto, response.getBody());
    }

    @Test
    void deveListarDocumentosPorAcao() {
        Long acaoId = 1L;
        List<DocumentoResponseDto> documentoResponseDtos = List.of(new DocumentoResponseDto(), new DocumentoResponseDto());

        when(documentoService.listarDocumentosPorAcao(acaoId)).thenReturn(documentoResponseDtos);

        ResponseEntity<List<DocumentoResponseDto>> response = documentoController.listarDocumentosPorAcao(acaoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentoResponseDtos, response.getBody());
    }

    @Test
    void deveObterDocumento() {
        Long documentoId = 1L;
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(documentoService.obterDocumentoResponseDto(documentoId)).thenReturn(documentoResponseDto);

        ResponseEntity<DocumentoResponseDto> response = documentoController.obterDocumento(documentoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentoResponseDto, response.getBody());
    }

    @Test
    void deveDownloadDocumento() throws IOException {
        Long documentoId = 1L;
        Documento documento = new Documento();
        documento.setNome("documento.txt");
        documento.setConteudo("conteudo do documento".getBytes());

        when(documentoService.obterDocumento(documentoId)).thenReturn(documento);

        ResponseEntity<byte[]> response = documentoController.downloadDocumento(documentoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("attachment; filename=\"documento.txt\"", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertArrayEquals("conteudo do documento".getBytes(), response.getBody());
    }

    @Test
    void deveAtualizarDocumento() {
        Long documentoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(documentoService.atualizarDocumento(documentoId, documentoDto)).thenReturn(documentoResponseDto);

        ResponseEntity<DocumentoResponseDto> response = documentoController.atualizarDocumento(documentoId, documentoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentoResponseDto, response.getBody());
    }

    @Test
    void deveExcluirDocumento() {
        Long documentoId = 1L;

        ResponseEntity<Void> response = documentoController.excluirDocumento(documentoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(documentoService).excluirDocumento(documentoId);
    }
}