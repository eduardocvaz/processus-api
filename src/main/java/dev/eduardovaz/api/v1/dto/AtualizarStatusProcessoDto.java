package dev.eduardovaz.api.v1.dto;

import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarStatusProcessoDto {
    private StatusProcesso status;
}
