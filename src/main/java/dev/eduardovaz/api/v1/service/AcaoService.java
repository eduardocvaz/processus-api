package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.mapper.AcaoMapper;
import dev.eduardovaz.api.v1.model.Acao;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.repository.AcaoRepository;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import dev.eduardovaz.core.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcaoService extends BaseService<Acao, AcaoRepository> {

    @Autowired
    private ProcessoRepository processoRepository;

    private final AcaoMapper acaoMapper;

    public AcaoResponseDto registrarAcao(Long processoId, AcaoDto acaoDto) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));


        acaoDto.setProcessoId(processoId);
        return acaoMapper.toAcaoResponseDto(repository.save(acaoMapper.toAcao(acaoDto)));
    }

    public List<AcaoResponseDto> listarAcoesPorProcesso(Long processoId) {
        List<Acao> acaos = repository.findByProcessoId(processoId);
        return acaos.stream()
                .map(acaoMapper::toAcaoResponseDto) // Mapear para DTO
                .collect(Collectors.toList());
    }

    public AcaoResponseDto atualizarAcao(Long acaoId, AcaoDto acaoDto) {
        Acao acao = repository.findById(acaoId)
                .orElseThrow(() -> new RuntimeException("Ação não encontrada"));

        return acaoMapper.toAcaoResponseDto(repository.save(acaoMapper.toAcao(acaoDto)));
    }

    public void excluirAcao(Long acaoId) {
        repository.deleteById(acaoId);
    }
}
