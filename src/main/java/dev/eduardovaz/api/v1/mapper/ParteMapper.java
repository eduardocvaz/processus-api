package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.model.Parte;
import dev.eduardovaz.api.v1.model.Processo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParteMapper {
    private final ModelMapper mapper;
    public Parte toParte(ParteDto p) {
        return mapper.map(p, Parte.class);
    }

    public ParteResponseDto toParteResponseDto(Parte parte) {
        return mapper.map(parte, ParteResponseDto.class);
    }
}
