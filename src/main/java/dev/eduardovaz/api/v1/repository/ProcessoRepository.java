package dev.eduardovaz.api.v1.repository;

import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.core.base.BaseRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProcessoRepository extends BaseRepository<Processo> {
    List<Processo> findAll(Specification<Processo> spec);
}
