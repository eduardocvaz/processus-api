package dev.eduardovaz.api.v1.dto;

import dev.eduardovaz.api.v1.model.enums.TipoAcao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AcaoResponseDto {
    private Long id;
    private TipoAcao tipo;
    private LocalDate dataRegistro;
    private String descricao;
    private List<DocumentoResponseDto> documentos;
}
