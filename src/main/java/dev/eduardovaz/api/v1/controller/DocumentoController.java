package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.api.v1.service.DocumentoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/acoes/{acaoId}")
    public ResponseEntity<DocumentoResponseDto> criarDocumento(
            @Parameter(description = "Identificador da Ação") @PathVariable(value = "acaoId") Long acaoId,
            @RequestBody DocumentoDto documentoDto)
    {
        DocumentoResponseDto novoDocumento = documentoService.criarDocumento(acaoId, documentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
    }

    @GetMapping("/acoes/{acaoId}")
    public ResponseEntity<List<DocumentoResponseDto>> listarDocumentosPorAcao(
            @Parameter(description = "Identificador da Ação") @PathVariable(value = "acaoId") Long acaoId)
    {
        List<DocumentoResponseDto> documentos = documentoService.listarDocumentosPorAcao(acaoId);
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{documentoId}")
    public ResponseEntity<DocumentoResponseDto> obterDocumento(
            @Parameter(description = "Identificador do Documento") @PathVariable(value = "documentoId") Long documentoId)
    {
        DocumentoResponseDto documento = documentoService.obterDocumentoResponseDto(documentoId);
        return ResponseEntity.ok(documento);
    }

    @GetMapping("/{documentoId}/conteudo")
    public ResponseEntity<byte[]> downloadDocumento(
            @Parameter(description = "Identificador do Documento") @PathVariable(value = "documentoId") Long documentoId)
    {
        Documento documento = documentoService.obterDocumento(documentoId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNome() + "\"")
                .body(documento.getConteudo());
    }

    @PutMapping("/{documentoId}")
    public ResponseEntity<DocumentoResponseDto> atualizarDocumento(
            @PathVariable Long documentoId,
            @RequestBody DocumentoDto documentoDto) {

        DocumentoResponseDto documentoAtualizado = documentoService.atualizarDocumento(documentoId, documentoDto);
        return ResponseEntity.ok(documentoAtualizado);
    }

    @DeleteMapping("/{documentoId}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable Long documentoId) {
        documentoService.excluirDocumento(documentoId);
        return ResponseEntity.noContent().build();
    }
}
