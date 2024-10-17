package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.model.Acao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AcaoMapper {
    private final ModelMapper mapper;
    public Acao toAcao(AcaoDto p) {
        return mapper.map(p, Acao.class);
    }

    public AcaoResponseDto toAcaoResponseDto(Acao acao) {
        return mapper.map(acao, AcaoResponseDto.class);
    }
}
