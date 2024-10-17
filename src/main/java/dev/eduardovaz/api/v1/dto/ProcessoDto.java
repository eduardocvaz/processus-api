package dev.eduardovaz.api.v1.dto;

import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProcessoDto {
private Long id;
private String numero;
private LocalDate dataAbertura;
private String descricao;
private StatusProcesso status;
}
