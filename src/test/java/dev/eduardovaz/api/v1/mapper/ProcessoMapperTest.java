package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.model.Processo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessoMapperTest {

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ProcessoMapper processoMapper;

    @Test
    void deveConverterProcessoDtoParaProcesso() {
        ProcessoDto processoDto = new ProcessoDto();
        processoDto.setNumero("123456789");
        Processo processo = new Processo();
        processo.setNumero("123456789");

        when(mapper.map(processoDto, Processo.class)).thenReturn(processo);

        Processo processoConvertido = processoMapper.toProcesso(processoDto);

        assertEquals(processo, processoConvertido);
    }

    @Test
    void deveConverterProcessoParaProcessoResponseDto() {
        Processo processo = new Processo();
        processo.setNumero("123456789");
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();
        processoResponseDto.setNumero("123456789");

        when(mapper.map(processo, ProcessoResponseDto.class)).thenReturn(processoResponseDto);

        ProcessoResponseDto processoResponseDtoConvertido = processoMapper.toProcessoResponseDto(processo);

        assertEquals(processoResponseDto, processoResponseDtoConvertido);
    }
}