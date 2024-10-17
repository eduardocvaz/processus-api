package dev.eduardovaz.api.v1.dto;

import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProcessoResponseDto {
    private Long id;
    private String numero;
    private LocalDate dataAbertura;
    private String descricao;
    private StatusProcesso status;
    private List<ParteResponseDto> partes;
}
