package dev.eduardovaz.api.v1.specification;

import dev.eduardovaz.api.v1.mapper.ProcessoMapper;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import dev.eduardovaz.api.v1.repository.ProcessoRepository;
import dev.eduardovaz.api.v1.service.ProcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessoSpecificationTest {

    @Test
    void comId() {
        Specification<Processo> spec = ProcessoSpecification.comId(1L);
        assertNotNull(spec);
    }

    @Test
    void comCpfCnpjParte() {
        Specification<Processo> spec = ProcessoSpecification.comCpfCnpjParte("12345678901");
        assertNotNull(spec);
    }

    @Test
    void comStatus() {
        Specification<Processo> spec = ProcessoSpecification.comStatus(StatusProcesso.ATIVO);
        assertNotNull(spec);
    }

    @Test
    void comDataAberturaMenorQue() {
        Specification<Processo> spec = ProcessoSpecification.comDataAberturaMenorQue(LocalDate.now());
        assertNotNull(spec);
    }

    @Test
    void comDataAberturaEntre() {
        LocalDate dataInicio = LocalDate.of(2024, 10, 10);
        LocalDate dataFim = LocalDate.of(2024, 10, 20);
        Specification<Processo> spec = ProcessoSpecification.comDataAberturaEntre(dataInicio, dataFim);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComId() {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(1L, null, null, null, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComStatus() {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(null, StatusProcesso.ATIVO, null, null, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComCpfCnpjParte() {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(null, null, null, null, "12345678901");
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComDataAberturaEntre() {
        LocalDate dataInicio = LocalDate.of(2024, 10, 10);
        LocalDate dataFim = LocalDate.of(2024, 10, 20);
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(null, null, dataInicio, dataFim, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComDataAberturaMenorQue() {
        LocalDate dataInicio = LocalDate.of(2024, 10, 18);
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(null, null, dataInicio, null, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComIdEStatus() {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(1L, StatusProcesso.ATIVO, null, null, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComIdECpfCnpjParte() {
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(1L, null, null, null, "12345678901");
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComIdEDataAberturaEntre() {
        LocalDate dataInicio = LocalDate.of(2024, 10, 10);
        LocalDate dataFim = LocalDate.of(2024, 10, 20);
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(1L, null, dataInicio, dataFim, null);
        assertNotNull(spec);
    }

    @Test
    void buscarProcessosComMultiplosCriterios() {
        LocalDate dataInicio = LocalDate.of(2024, 10, 10);
        LocalDate dataFim = LocalDate.of(2024, 10, 20);
        Specification<Processo> spec = ProcessoSpecification.buscarProcessos(1L, StatusProcesso.ATIVO, dataInicio, dataFim, "12345678901");
        assertNotNull(spec);
    }
}