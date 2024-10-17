package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.DocumentoDto;
import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentoMapperTest {

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private DocumentoMapper documentoMapper;

    @Test
    void deveConverterDocumentoDtoParaDocumento() {
        DocumentoDto documentoDto = new DocumentoDto();
        documentoDto.setNome("documento.txt");
        Documento documento = new Documento();
        documento.setNome("documento.txt");

        when(mapper.map(documentoDto, Documento.class)).thenReturn(documento);

        Documento documentoConvertido = documentoMapper.toDocumento(documentoDto);

        assertEquals(documento, documentoConvertido);
    }

    @Test
    void deveConverterDocumentoParaDocumentoResponseDto() {
        Documento documento = new Documento();
        documento.setNome("documento.txt");
        DocumentoResponseDto documentoResponseDto = new DocumentoResponseDto();
        documentoResponseDto.setNome("documento.txt");

        when(mapper.map(documento, DocumentoResponseDto.class)).thenReturn(documentoResponseDto);

        DocumentoResponseDto documentoResponseDtoConvertido = documentoMapper.toDocumentoResponseDto(documento);

        assertEquals(documentoResponseDto, documentoResponseDtoConvertido);
    }
}