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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService extends BaseService<Documento, DocumentoRepository> {

    @Autowired
    AcaoRepository acaoRepository;

    private final DocumentoMapper documentoMapper;

    private static final String DOCUMENTO_NAO_ENCONTRADO = "Documento não encontrado";

    public DocumentoResponseDto criarDocumento(Long acaoId, DocumentoDto documentoDto) {
        if (!acaoRepository.existsById(acaoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ação não encontrada");
        }

        documentoDto.setAcaoId(acaoId);
        return documentoMapper.toDocumentoResponseDto(repository.save(documentoMapper.toDocumento(documentoDto)));
    }

    public List<DocumentoResponseDto> listarDocumentosPorAcao(Long acaoId) {
        return repository.findAllByAcaoIdWithoutContent(acaoId);
    }

    public Documento obterDocumento(Long documentoId) {
        return repository.findById(documentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, DOCUMENTO_NAO_ENCONTRADO));
    }

    public DocumentoResponseDto obterDocumentoResponseDto(Long documentoId) {
        return documentoMapper.toDocumentoResponseDto(repository.findById(documentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, DOCUMENTO_NAO_ENCONTRADO)));
    }

    public DocumentoResponseDto atualizarDocumento(Long documentoId, DocumentoDto documentoDto) {
        if (!repository.existsById(documentoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, DOCUMENTO_NAO_ENCONTRADO);
        }

        return documentoMapper.toDocumentoResponseDto(repository.save(documentoMapper.toDocumento(documentoDto)));
    }

    public void excluirDocumento(Long documentoId) {
        repository.deleteById(documentoId);
    }

}
