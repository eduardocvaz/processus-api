package dev.eduardovaz.api.v1.repository;

import dev.eduardovaz.api.v1.dto.DocumentoResponseDto;
import dev.eduardovaz.api.v1.model.Documento;
import dev.eduardovaz.core.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoRepository extends BaseRepository<Documento> {
    @Query("SELECT new dev.eduardovaz.api.v1.dto.DocumentoResponseDto(d.id, d.nome, d.tipo, d.tamanho) FROM Documento d WHERE d.acao.id = :acaoId ORDER BY d.nome ASC")
    List<DocumentoResponseDto> findAllByAcaoIdWithoutContent(@Param("acaoId") Long acaoId);
}
