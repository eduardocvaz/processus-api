package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentoMapper {
    private final ModelMapper mapper;
    public Documento toDocumento(DocumentoDto p) {
        return mapper.map(p, Documento.class);
    }

    public DocumentoResponseDto toDocumentoResponseDto(Documento documento) {
        return mapper.map(documento, DocumentoResponseDto.class);
    }
}
