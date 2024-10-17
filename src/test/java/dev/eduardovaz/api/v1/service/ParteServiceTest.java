package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.ParteDto;
import dev.eduardovaz.api.v1.dto.ParteResponseDto;
import dev.eduardovaz.api.v1.mapper.ParteMapper;
import dev.eduardovaz.api.v1.model.Parte;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.repository.ParteRepository;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParteServiceTest {

    @Mock
    private ParteRepository parteRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private ParteMapper parteMapper;

    @InjectMocks
    private ParteService parteService;

    @BeforeEach
    void setUp() {
        // Inicializar o repository manualmente
        parteService.repository = parteRepository;
        parteService.processoRepository = processoRepository;

    }
    @Test
    void deveSalvarParte() {
        ParteDto parteDto = new ParteDto();
        Parte parte = new Parte();
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteMapper.toParte(parteDto)).thenReturn(parte);
        when(parteRepository.save(parte)).thenReturn(parte);
        when(parteMapper.toParteResponseDto(parte)).thenReturn(parteResponseDto);

        ParteResponseDto parteSalva = parteService.save(parteDto);

        assertNotNull(parteSalva);
        assertEquals(parteResponseDto, parteSalva);

        verify(parteMapper).toParte(parteDto);
        verify(parteRepository).save(parte);
        verify(parteMapper).toParteResponseDto(parte);
    }

    @Test
    void deveAssociarPartesAoProcesso() {
        Long processoId = 1L;
        List<Long> partesIds = List.of(1L, 2L);
        Processo processo = new Processo();
        Parte parte1 = new Parte();
        Parte parte2 = new Parte();

        processo.setPartes(new ArrayList<>()); // Inicializar a lista aqui

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(parteRepository.findById(1L)).thenReturn(Optional.of(parte1));
        when(parteRepository.findById(2L)).thenReturn(Optional.of(parte2));

        parteService.associarPartesAoProcesso(partesIds, processoId);

        assertTrue(processo.getPartes().contains(parte1));
        assertTrue(processo.getPartes().contains(parte2));

        verify(processoRepository).save(processo);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoEncontrado() {
        Long processoId = 1L;
        List<Long> partesIds = List.of(1L);

        when(processoRepository.findById(processoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> parteService.associarPartesAoProcesso(partesIds, processoId));
    }

    @Test
    void deveLancarExcecaoQuandoParteNaoEncontrada() {
        Long processoId = 1L;
        List<Long> partesIds = List.of(1L);
        Processo processo = new Processo();

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(parteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> parteService.associarPartesAoProcesso(partesIds, processoId));
    }

    @Test
    void deveObterPartePorId() {
        Long parteId = 1L;
        Parte parte = new Parte();
        parte.setId(parteId);
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteRepository.findById(parteId)).thenReturn(Optional.of(parte));
        when(parteMapper.toParteResponseDto(parte)).thenReturn(parteResponseDto);

        ParteResponseDto parteEncontrada = parteService.obterParte(parteId);

        assertNotNull(parteEncontrada);
        assertEquals(parteResponseDto, parteEncontrada);
    }

    @Test
    void deveLancarExcecaoQuandoParteNaoEncontradaAoObterPorId() {
        Long parteId = 1L;

        when(parteRepository.findById(parteId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> parteService.obterParte(parteId));
    }

    @Test
    void deveListarPartes() {
        List<Parte> partes = List.of(new Parte(), new Parte());
        List<ParteResponseDto> parteResponseDtos = List.of(new ParteResponseDto(), new ParteResponseDto());

        when(parteRepository.findAll()).thenReturn(partes);
        when(parteMapper.toParteResponseDto(partes.get(0))).thenReturn(parteResponseDtos.get(0));
        when(parteMapper.toParteResponseDto(partes.get(1))).thenReturn(parteResponseDtos.get(1));

        List<ParteResponseDto> partesEncontradas = parteService.listarPartes();

        assertNotNull(partesEncontradas);
        assertEquals(2, partesEncontradas.size());
    }

    @Test
    void deveEditarParte() {
        Long parteId = 1L;
        ParteDto parteDto = new ParteDto();
        parteDto.setId(parteId);
        Parte parte = new Parte();
        parte.setId(parteId);
        ParteResponseDto parteResponseDto = new ParteResponseDto();

        when(parteRepository.findById(parteId)).thenReturn(Optional.of(parte));
        when(parteMapper.toParte(parteDto)).thenReturn(parte);
        when(parteRepository.save(parte)).thenReturn(parte);
        when(parteMapper.toParteResponseDto(parte)).thenReturn(parteResponseDto);

        ParteResponseDto parteEditada = parteService.editar(parteDto);

        assertNotNull(parteEditada);
        assertEquals(parteResponseDto, parteEditada);

        verify(parteRepository).save(parte);
    }

    @Test
    void deveLancarExcecaoQuandoParteNaoEncontradaAoEditar() {
        Long parteId = 1L;
        ParteDto parteDto = new ParteDto();
        parteDto.setId(parteId);

        when(parteRepository.findById(parteId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> parteService.editar(parteDto));
    }
}