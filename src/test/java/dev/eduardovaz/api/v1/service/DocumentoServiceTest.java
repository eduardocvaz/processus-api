package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.mapper.DocumentoMapper;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.api.v1.repository.AcaoRepository;
import dev.eduardovaz.api.v1.repository.DocumentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentoServiceTest {

    @Mock
    private DocumentoRepository documentoRepository;

    @Mock
    private AcaoRepository acaoRepository;

    @Mock
    private DocumentoMapper documentoMapper;

    @InjectMocks
    private DocumentoService documentoService;

    @BeforeEach
    void setUp() {
        documentoService.repository = documentoRepository; // Inicializar o repository manualmente
        documentoService.acaoRepository = acaoRepository;
    }

    @Test
    void deveCriarDocumento() {
        Long acaoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();
        Documento documento = new Documento();
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(acaoRepository.existsById(acaoId)).thenReturn(true);
        when(documentoMapper.toDocumento(documentoDto)).thenReturn(documento);
        when(documentoRepository.save(documento)).thenReturn(documento);
        when(documentoMapper.toDocumentoResponseDto(documento)).thenReturn(documentoResponseDto);

        DocumentoResponseDto documentoCriado = documentoService.criarDocumento(acaoId, documentoDto);

        assertNotNull(documentoCriado);
        assertEquals(documentoResponseDto, documentoCriado);

        verify(documentoMapper).toDocumento(documentoDto);
        verify(documentoRepository).save(documento);
        verify(documentoMapper).toDocumentoResponseDto(documento);
    }

    @Test
    void deveLancarExcecaoQuandoAcaoNaoEncontradaAoCriar() {
        Long acaoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();

        when(acaoRepository.existsById(acaoId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> documentoService.criarDocumento(acaoId, documentoDto));
    }

    @Test
    void deveListarDocumentosPorAcao() {
        Long acaoId = 1L;
        List<DocumentoResponseDto> documentoResponseDtos = List.of(new DocumentoResponseDto(), new DocumentoResponseDto());

        when(documentoRepository.findAllByAcaoIdWithoutContent(acaoId)).thenReturn(documentoResponseDtos);

        List<DocumentoResponseDto> documentosEncontrados = documentoService.listarDocumentosPorAcao(acaoId);

        assertNotNull(documentosEncontrados);
        assertEquals(2, documentosEncontrados.size());
        assertEquals(documentoResponseDtos, documentosEncontrados);
    }

    @Test
    void deveObterDocumentoPorId() {
        Long documentoId = 1L;
        Documento documento = new Documento();

        when(documentoRepository.findById(documentoId)).thenReturn(Optional.of(documento));

        Documento documentoEncontrado = documentoService.obterDocumento(documentoId);

        assertNotNull(documentoEncontrado);
        assertEquals(documento, documentoEncontrado);
    }

    @Test
    void deveLancarExcecaoQuandoDocumentoNaoEncontradoAoObterPorId() {
        Long documentoId = 1L;

        when(documentoRepository.findById(documentoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> documentoService.obterDocumento(documentoId));
    }

    @Test
    void deveObterDocumentoResponseDtoPorId() {
        Long documentoId = 1L;
        Documento documento = new Documento();
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(documentoRepository.findById(documentoId)).thenReturn(Optional.of(documento));
        when(documentoMapper.toDocumentoResponseDto(documento)).thenReturn(documentoResponseDto);

        DocumentoResponseDto documentoEncontrado = documentoService.obterDocumentoResponseDto(documentoId);

        assertNotNull(documentoEncontrado);
        assertEquals(documentoResponseDto, documentoEncontrado);
    }

    @Test
    void deveLancarExcecaoQuandoDocumentoNaoEncontradoAoObterResponseDtoPorId() {
        Long documentoId = 1L;

        when(documentoRepository.findById(documentoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> documentoService.obterDocumentoResponseDto(documentoId));
    }

    @Test
    void deveAtualizarDocumento() {
        Long documentoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();
        Documento documento = new Documento();
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();

        when(documentoRepository.existsById(documentoId)).thenReturn(true);
        when(documentoMapper.toDocumento(documentoDto)).thenReturn(documento);
        when(documentoRepository.save(documento)).thenReturn(documento);
        when(documentoMapper.toDocumentoResponseDto(documento)).thenReturn(documentoResponseDto);

        DocumentoResponseDto documentoAtualizado = documentoService.atualizarDocumento(documentoId, documentoDto);

        assertNotNull(documentoAtualizado);
        assertEquals(documentoResponseDto, documentoAtualizado);

        verify(documentoMapper).toDocumento(documentoDto);
        verify(documentoRepository).save(documento);
        verify(documentoMapper).toDocumentoResponseDto(documento);
    }

    @Test
    void deveLancarExcecaoQuandoDocumentoNaoEncontradoAoAtualizar() {
        Long documentoId = 1L;
        DocumentoDto documentoDto = new DocumentoDto();

        when(documentoRepository.existsById(documentoId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> documentoService.atualizarDocumento(documentoId, documentoDto));
    }

    @Test
    void deveExcluirDocumento() {
        Long documentoId = 1L;

        documentoService.excluirDocumento(documentoId);

        verify(documentoRepository).deleteById(documentoId);
    }
}