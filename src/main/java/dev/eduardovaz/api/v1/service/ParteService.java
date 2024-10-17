package dev.eduardovaz.api.v1.service;


import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.mapper.ParteMapper;
import dev.eduardovaz.api.v1.model.Parte;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.repository.ParteRepository;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import dev.eduardovaz.core.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParteService extends BaseService<Parte, ParteRepository> {

    private final ParteMapper parteMapper;

    @Autowired
    private ProcessoRepository processoRepository;
    public ParteResponseDto save(ParteDto parte) {
        // Validações de negócio, se necessário
        return parteMapper.toParteResponseDto(repository.save(parteMapper.toParte(parte)));
    }

    public void associarPartesAoProcesso(List<Long> partesIds, Long processoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));

        for (Long parteId : partesIds) {
            Parte parte = repository.findById(parteId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parte com ID " + parteId + " não encontrada"));
            processo.getPartes().add(parte);
        }
        processoRepository.save(processo);
    }

    public ParteResponseDto obterParte(Long parteId) {
        return parteMapper.toParteResponseDto(repository.findById(parteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado")));
    }

    public List<ParteResponseDto> listarPartes() {
        List<Parte> partes = repository.findAll();
        return partes.stream()
                .map(parteMapper::toParteResponseDto) // Mapear para DTO
                .toList();
    }

    public ParteResponseDto editar(ParteDto parte) {
        repository.findById(parte.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parte não encontrada"));//
        return parteMapper.toParteResponseDto(repository.save(parteMapper.toParte(parte)));
    }

}
