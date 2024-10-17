package dev.eduardovaz.api.v1.mapper;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.model.Acao;
import dev.eduardovaz.api.v1.model.enums.TipoAcao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AcaoMapperTest {
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private AcaoMapper acaoMapper;

    @Test
    void deveConverterAcaoDtoParaAcao() {
        AcaoDto acaoDto = new AcaoDto();
        acaoDto.setTipo(TipoAcao.SENTENCA);
        Acao acao = new Acao();
        acao.setTipo(TipoAcao.SENTENCA);

        when(mapper.map(acaoDto, Acao.class)).thenReturn(acao);

        Acao acaoConvertida = acaoMapper.toAcao(acaoDto);

        assertEquals(acao, acaoConvertida);
    }

    @Test
    void deveConverterAcaoParaAcaoResponseDto() {
        Acao acao = new Acao();
        acao.setTipo(TipoAcao.SENTENCA);
        AcaoResponseDto acaoResponseDto = new AcaoResponseDto();
        acaoResponseDto.setTipo(TipoAcao.SENTENCA);

        when(mapper.map(acao, AcaoResponseDto.class)).thenReturn(acaoResponseDto);

        AcaoResponseDto acaoResponseDtoConvertido = acaoMapper.toAcaoResponseDto(acao);

        assertEquals(acaoResponseDto, acaoResponseDtoConvertido);
    }
}