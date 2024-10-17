package dev.eduardovaz.api.v1.controller;

import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.model.Parte;
import dev.eduardovaz.api.v1.service.ParteService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/partes")
public class ParteController {

    @Autowired
    private ParteService parteService;

    @PostMapping()
    public ResponseEntity<ParteResponseDto> criar(@RequestBody ParteDto parte) {
        ParteResponseDto novaParte = parteService.save(parte);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaParte);
    }

    @PutMapping()
    public ResponseEntity<ParteResponseDto> editar(@RequestBody ParteDto parte) {
        ParteResponseDto parteAtualizada = parteService.editar(parte);
        return ResponseEntity.ok(parteAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParteResponseDto> consultarPorId(@Parameter(description = "Identificador da Parte") @PathVariable(value = "id") Long id) {
        ParteResponseDto parte = parteService.obterParte(id);
        return ResponseEntity.ok(parte);
    }

    @GetMapping()
    public ResponseEntity<List<ParteResponseDto>> consultar() {
        List<ParteResponseDto> partes = parteService.listarPartes();
        return ResponseEntity.ok(partes);
    }

    @PostMapping("/{processoId}")
    public ResponseEntity<Void> associarPartesAoProcesso(@Parameter(description = "Identificador dos Processo") @PathVariable(value = "processoId") Long processoId, @RequestBody List<Long> partesIds) {
        parteService.associarPartesAoProcesso(partesIds, processoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Identificador da Parte") @PathVariable(value = "id") Long id) {
        parteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}