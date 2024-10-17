package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.AtualizarStatusProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.service.ProcessoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {
    @Autowired
    private ProcessoService processoService;

    @PostMapping()
    public ResponseEntity<ProcessoResponseDto> criar(@RequestBody ProcessoDto processo) {
        ProcessoResponseDto novoProcesso = processoService.save(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProcesso);
    }

    @PutMapping()
    public ResponseEntity<ProcessoResponseDto> editar(@RequestBody ProcessoDto processo) {
        ProcessoResponseDto processoAtualizado = processoService.editar(processo);
        return ResponseEntity.ok(processoAtualizado);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusProcesso(
            @Parameter(description = "Identificador do Processo") @PathVariable(value = "id") Long id,
            @RequestBody AtualizarStatusProcessoDto dto) {
        processoService.atualizarStatusProcesso(id, dto.getStatus());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoResponseDto> consultarPorId(@Parameter(description = "Identificador do Processo") @PathVariable(value = "id") Long id) {
        ProcessoResponseDto processo = processoService.obterProcesso(id);
        return ResponseEntity.ok(processo);
    }

    @GetMapping()
    public ResponseEntity<List<ProcessoResponseDto>> consultar() {
        List<ProcessoResponseDto> processos = processoService.listarProcessos();
        return ResponseEntity.ok(processos);
    }

    @PatchMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@RequestBody List<Long> ids) {
        processoService.arquivar(ids);
        return ResponseEntity.noContent().build();
    }
}
