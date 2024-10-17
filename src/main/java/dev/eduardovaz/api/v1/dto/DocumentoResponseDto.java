package dev.eduardovaz.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocumentoResponseDto {
    private Long id;
    private String nome;
    private String tipo;
    private Long tamanho;
}
