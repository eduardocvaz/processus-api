package dev.eduardovaz.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoDto {
    private Long id;
    private String nome;
    private String tipo;
    private Long tamanho;
    private byte[] conteudo;
    private Long acaoId;
}
