package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.mapper.ProcessoMapper;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import dev.eduardovaz.api.v1.specification.ProcessoSpecification;
import dev.eduardovaz.core.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessoService extends BaseService<Processo, ProcessoRepository> {

    private final ProcessoMapper processoMapper;

    public List<ProcessoResponseDto> buscarProcessos(Long id, StatusProcesso status, LocalDate dataInicio, LocalDate dataFim, String cpfCnpj) {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(id, status, dataInicio, dataFim, cpfCnpj);
        List<Processo> processos = repository.findAll(spec);
        return processos.stream()
                .map(processoMapper::toProcessoResponseDto) // Mapear para DTO
                .toList();
    }

    public ProcessoResponseDto save(ProcessoDto processo) {
        // Validações de negócio, se necessários
        return processoMapper.toProcessoResponseDto(repository.save(processoMapper.toProcesso(processo)));
    }

    public ProcessoResponseDto editar(ProcessoDto processoDto) {
        repository.findById(processoDto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrada"));//
        return processoMapper.toProcessoResponseDto(repository.save(processoMapper.toProcesso(processoDto)));
    }

    public void atualizarStatusProcesso(Long id, StatusProcesso novoStatus) {
        Processo processo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));

        processo.setStatus(novoStatus);
        repository.save(processo);
    }

    public ProcessoResponseDto obterProcesso(Long processoId) {
        return processoMapper.toProcessoResponseDto(repository.findById(processoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado")));
    }

    public List<ProcessoResponseDto> listarProcessos() {
        List<Processo> partes = repository.findAll();
        return partes.stream()
                .map(processoMapper::toProcessoResponseDto) // Mapear para DTO
                .toList();
    }

    public void arquivar(List<Long> ids) {
        // Buscar os processos pelos IDs
        List<Processo> processos = repository.findAllById(ids);

        // Arquivar os processos
        for (Processo processo : processos) {
            processo.setStatus(StatusProcesso.ARQUIVADO);
        }

        // Salvar os processos arquivados
        repository.saveAll(processos);
    }

}
