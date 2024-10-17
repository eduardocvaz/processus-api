package dev.eduardovaz.api.v1.repository;

import dev.eduardovaz.api.v1.model.Acao;
import dev.eduardovaz.core.base.BaseRepository;

import java.util.List;

public interface AcaoRepository extends BaseRepository<Acao> {

    List<Acao> findByProcessoId(Long processoId);
}
