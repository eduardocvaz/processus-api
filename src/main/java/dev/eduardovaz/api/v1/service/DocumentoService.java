package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.mapper.DocumentoMapper;
import dev.eduardovaz.api.v1.model.Acao;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.api.v1.repository.AcaoRepository;
import dev.eduardovaz.api.v1.repository.DocumentoRepository;
import dev.eduardovaz.core.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentoService extends BaseService<Documento, DocumentoRepository> {

    @Autowired
    private AcaoRepository acaoRepository;

    private final DocumentoMapper documentoMapper;

    public DocumentoResponseDto criarDocumento(Long acaoId, DocumentoDto documentoDto) {
        Acao acao = acaoRepository.findById(acaoId)
                .orElseThrow(() -> new RuntimeException("Ação não encontrada"));

        documentoDto.setAcaoId(acaoId);
        return documentoMapper.toDocumentoResponseDto(repository.save(documentoMapper.toDocumento(documentoDto)));
    }

    public List<DocumentoResponseDto> listarDocumentosPorAcao(Long acaoId) {
        return repository.findAllByAcaoIdWithoutContent(acaoId);
    }

    public Documento obterDocumento(Long documentoId) {
        return repository.findById(documentoId)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));
    }

    public DocumentoResponseDto obterDocumentoResponseDto(Long documentoId) {
        return documentoMapper.toDocumentoResponseDto(repository.findById(documentoId)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado")));
    }

    public DocumentoResponseDto atualizarDocumento(Long documentoId, DocumentoDto documentoDto) {
        Documento documento = repository.findById(documentoId)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        return documentoMapper.toDocumentoResponseDto(repository.save(documentoMapper.toDocumento(documentoDto)));
    }

    public void excluirDocumento(Long documentoId) {
        repository.deleteById(documentoId);
    }

}
