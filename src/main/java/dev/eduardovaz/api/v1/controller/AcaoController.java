package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.service.AcaoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acoes")
public class AcaoController {
    @Autowired
    private AcaoService acaoService;

    @PostMapping("/processos/{processoId}")
    public ResponseEntity<AcaoResponseDto> registrarAcao(
            @Parameter(description = "Identificador do Processo") @PathVariable(value = "processoId") Long processoId,
            @RequestBody AcaoDto acaoDto) {
        AcaoResponseDto novaAcao = acaoService.registrarAcao(processoId, acaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAcao);
    }

    @GetMapping("/processos/{processoId}")
    public ResponseEntity<List<AcaoResponseDto>> listarAcoesPorProcesso(@Parameter(description = "Identificador do Processo") @PathVariable(value = "processoId") Long processoId) {
        List<AcaoResponseDto> acoes = acaoService.listarAcoesPorProcesso(processoId);
        return ResponseEntity.ok(acoes);
    }

    @PutMapping("/{acaoId}")
    public ResponseEntity<AcaoResponseDto> atualizarAcao(
            @Parameter(description = "Identificador da Ação") @PathVariable(value = "acaoId") Long acaoId,
            @RequestBody AcaoDto acaoDto) {
        AcaoResponseDto acaoAtualizada = acaoService.atualizarAcao(acaoId, acaoDto);
        return ResponseEntity.ok(acaoAtualizada);
    }

    @DeleteMapping("/{acaoId}")
    public ResponseEntity<Void> excluirAcao(@Parameter(description = "Identificador da Ação") @PathVariable(value = "acaoId") Long acaoId) {
        acaoService.excluirAcao(acaoId);
        return ResponseEntity.noContent().build();
    }
}
