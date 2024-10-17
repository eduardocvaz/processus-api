package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.AcaoDto;
import dev.eduardovaz.api.v1.dto.AcaoResponseDto;
import dev.eduardovaz.api.v1.mapper.AcaoMapper;
import dev.eduardovaz.api.v1.model.Acao;
import dev.eduardovaz.api.v1.repository.AcaoRepository;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AcaoServiceTest {

    @Mock
    private AcaoRepository acaoRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private AcaoMapper acaoMapper;

    @InjectMocks
    private AcaoService acaoService;

    @BeforeEach
    void setUp() {
        acaoService.repository = acaoRepository; // Inicializar o repository manualmente
        acaoService.processoRepository = processoRepository;
    }

    @Test
    void deveRegistrarAcao() {
        Long processoId = 1L;
        AcaoDto acaoDto = new AcaoDto();
        Acao acao = new Acao();
        AcaoResponseDto acaoResponseDto = new AcaoResponseDto();

        when(processoRepository.existsById(processoId)).thenReturn(true);
        when(acaoMapper.toAcao(acaoDto)).thenReturn(acao);
        when(acaoRepository.save(acao)).thenReturn(acao);
        when(acaoMapper.toAcaoResponseDto(acao)).thenReturn(acaoResponseDto);

        AcaoResponseDto acaoRegistrada = acaoService.registrarAcao(processoId, acaoDto);

        assertNotNull(acaoRegistrada);
        assertEquals(acaoResponseDto, acaoRegistrada);

        verify(acaoMapper).toAcao(acaoDto);
        verify(acaoRepository).save(acao);
        verify(acaoMapper).toAcaoResponseDto(acao);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoEncontradoAoRegistrar() {
        Long processoId = 1L;
        AcaoDto acaoDto = new AcaoDto();

        when(processoRepository.existsById(processoId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> acaoService.registrarAcao(processoId, acaoDto));
    }

    @Test
    void deveListarAcoesPorProcesso() {
        Long processoId = 1L;
        List<Acao> acoes = List.of(new Acao(), new Acao());
        List<AcaoResponseDto> acaoResponseDtos = List.of(new AcaoResponseDto(), new AcaoResponseDto());

        when(acaoRepository.findByProcessoId(processoId)).thenReturn(acoes);
        when(acaoMapper.toAcaoResponseDto(acoes.get(0))).thenReturn(acaoResponseDtos.get(0));
        when(acaoMapper.toAcaoResponseDto(acoes.get(1))).thenReturn(acaoResponseDtos.get(1));

        List<AcaoResponseDto> acoesEncontradas = acaoService.listarAcoesPorProcesso(processoId);

        assertNotNull(acoesEncontradas);
        assertEquals(2, acoesEncontradas.size());
    }

    @Test
    void deveAtualizarAcao() {
        Long acaoId = 1L;
        AcaoDto acaoDto = new AcaoDto();
        Acao acao = new Acao();
        AcaoResponseDto acaoResponseDto = new AcaoResponseDto();

        when(acaoRepository.existsById(acaoId)).thenReturn(true);
        when(acaoMapper.toAcao(acaoDto)).thenReturn(acao);
        when(acaoRepository.save(acao)).thenReturn(acao);
        when(acaoMapper.toAcaoResponseDto(acao)).thenReturn(acaoResponseDto);

        AcaoResponseDto acaoAtualizada = acaoService.atualizarAcao(acaoId, acaoDto);

        assertNotNull(acaoAtualizada);
        assertEquals(acaoResponseDto, acaoAtualizada);

        verify(acaoMapper).toAcao(acaoDto);
        verify(acaoRepository).save(acao);
        verify(acaoMapper).toAcaoResponseDto(acao);
    }

    @Test
    void deveLancarExcecaoQuandoAcaoNaoEncontradaAoAtualizar() {
        Long acaoId = 1L;
        AcaoDto acaoDto = new AcaoDto();

        when(acaoRepository.existsById(acaoId)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> acaoService.atualizarAcao(acaoId, acaoDto));
    }

    @Test
    void deveExcluirAcao() {
        Long acaoId = 1L;

        acaoService.excluirAcao(acaoId);

        verify(acaoRepository).deleteById(acaoId);
    }
}