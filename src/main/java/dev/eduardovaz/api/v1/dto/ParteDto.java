package dev.eduardovaz.api.v1.dto;

import dev.eduardovaz.api.v1.model.enums.TipoParte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParteDto {
    private Long id;
    private String nomeCompleto;
    private String cpfCnpj;
    private TipoParte tipo;
    private String email;
    private String telefone;
}