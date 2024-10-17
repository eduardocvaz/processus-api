package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.api.v1.model.Processo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessoMapper {
    private final ModelMapper mapper;
    public Processo toProcesso(ProcessoDto p) {
        return mapper.map(p, Processo.class);
    }

    public ProcessoResponseDto toProcessoResponseDto(Processo processo) {
        return mapper.map(processo, ProcessoResponseDto.class);
    }
}
