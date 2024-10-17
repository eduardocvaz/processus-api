package dev.eduardovaz.api.v1.service;

import dev.eduardovaz.api.v1.dto.ProcessoDto;
import dev.eduardovaz.api.v1.dto.ProcessoResponseDto;
import dev.eduardovaz.api.v1.mapper.ProcessoMapper;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import dev.eduardovaz.api.v1.specification.ProcessoSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessoServiceTest {
    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private ProcessoMapper processoMapper;

    @InjectMocks
    private ProcessoService processoService;

    @BeforeEach
    void setUp() {
        // Inicializar o repository manualmente
        processoService.repository = processoRepository;
    }


    @Test
    void deveSalvarProcesso() {
        ProcessoDto processoDto = new ProcessoDto();
        Processo processo = new Processo();
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoMapper.toProcesso(processoDto)).thenReturn(processo);
        when(processoRepository.save(processo)).thenReturn(processo);
        when(processoMapper.toProcessoResponseDto(processo)).thenReturn(processoResponseDto);

        ProcessoResponseDto processoSalvo = processoService.save(processoDto);

        assertNotNull(processoSalvo);
        assertEquals(processoResponseDto, processoSalvo);

        verify(processoMapper).toProcesso(processoDto);
        verify(processoRepository).save(processo);
        verify(processoMapper).toProcessoResponseDto(processo);
    }

    @Test
    void deveEditarProcesso() {
        Long processoId = 1L;
        ProcessoDto processoDto = new ProcessoDto();
        processoDto.setId(processoId);
        Processo processo = new Processo();
        processo.setId(processoId);
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(processoMapper.toProcesso(processoDto)).thenReturn(processo);
        when(processoRepository.save(processo)).thenReturn(processo);
        when(processoMapper.toProcessoResponseDto(processo)).thenReturn(processoResponseDto);

        ProcessoResponseDto processoEditado = processoService.editar(processoDto);

        assertNotNull(processoEditado);
        assertEquals(processoResponseDto, processoEditado);

        verify(processoRepository).save(processo);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoEncontradoAoEditar() {
        Long processoId = 1L;
        ProcessoDto processoDto = new ProcessoDto();
        processoDto.setId(processoId);

        when(processoRepository.findById(processoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> processoService.editar(processoDto));
    }

    @Test
    void deveAtualizarStatusDoProcesso() {
        Long processoId = 1L;
        StatusProcesso novoStatus = StatusProcesso.ARQUIVADO;
        Processo processo = new Processo();
        processo.setId(processoId);

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));

        processoService.atualizarStatusProcesso(processoId, novoStatus);

        assertEquals(novoStatus, processo.getStatus());
        verify(processoRepository).save(processo);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoEncontradoAoAtualizarStatus() {
        Long processoId = 1L;
        StatusProcesso novoStatus = StatusProcesso.ARQUIVADO;

        when(processoRepository.findById(processoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> processoService.atualizarStatusProcesso(processoId, novoStatus));
    }

    @Test
    void deveObterProcessoPorId() {
        Long processoId = 1L;
        Processo processo = new Processo();
        processo.setId(processoId);
        ProcessoResponseDto processoResponseDto = new ProcessoResponseDto();

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(processoMapper.toProcessoResponseDto(processo)).thenReturn(processoResponseDto);

        ProcessoResponseDto processoEncontrado = processoService.obterProcesso(processoId);

        assertNotNull(processoEncontrado);
        assertEquals(processoResponseDto, processoEncontrado);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoEncontradoAoObterPorId() {
        Long processoId = 1L;

        when(processoRepository.findById(processoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> processoService.obterProcesso(processoId));
    }

    @Test
    void deveListarProcessos() {
        List<Processo> processos = List.of(new Processo(), new Processo());
        List<ProcessoResponseDto> processoResponseDtos = List.of(new ProcessoResponseDto(), new ProcessoResponseDto());

        when(processoRepository.findAll()).thenReturn(processos);
        when(processoMapper.toProcessoResponseDto(processos.get(0))).thenReturn(processoResponseDtos.get(0));
        when(processoMapper.toProcessoResponseDto(processos.get(1))).thenReturn(processoResponseDtos.get(1));

        List<ProcessoResponseDto> processosEncontrados = processoService.listarProcessos();

        assertNotNull(processosEncontrados);
        assertEquals(2, processosEncontrados.size());
    }

    @Test
    void deveArquivarProcessos() {
        List<Long> ids = List.of(1L, 2L);
        List<Processo> processos = List.of(
                new Processo("12345", LocalDate.now(), "Descrição", StatusProcesso.ATIVO, null),
                new Processo("98765", LocalDate.now(), "Descrição", StatusProcesso.ATIVO, null)
        );
        processos.get(0).setId(1L);
        processos.get(1).setId(2L);

        when(processoRepository.findAllById(ids)).thenReturn(processos);

        processoService.arquivar(ids);

        assertEquals(StatusProcesso.ARQUIVADO, processos.get(0).getStatus());
        assertEquals(StatusProcesso.ARQUIVADO, processos.get(1).getStatus());

        verify(processoRepository).saveAll(processos);
    }

    @Test
    void deveBuscarProcessos() {
        Long id = 1L;
        StatusProcesso status = StatusProcesso.ATIVO;
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);
        LocalDate dataFim = LocalDate.of(2024, 12, 31);
        String cpfCnpj = "12345678901";
        List<Processo> processos = List.of(new Processo(), new Processo());
        List<ProcessoResponseDto> processoResponseDtos = List.of(new ProcessoResponseDto(), new ProcessoResponseDto());

        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(id, status, dataInicio, dataFim, cpfCnpj);

        // Usar any(Specification.class) no when
        when(processoRepository.findAll(any(Specification.class))).thenReturn(processos);

        when(processoMapper.toProcessoResponseDto(processos.get(0))).thenReturn(processoResponseDtos.get(0));
        when(processoMapper.toProcessoResponseDto(processos.get(1))).thenReturn(processoResponseDtos.get(1));

        List<ProcessoResponseDto> processosEncontrados = processoService.buscarProcessos(id, status, dataInicio, dataFim, cpfCnpj);

        assertNotNull(processosEncontrados);
        assertEquals(2, processosEncontrados.size());

        verify(processoRepository).findAll(any(Specification.class)); // Verificar com any()
    }

}