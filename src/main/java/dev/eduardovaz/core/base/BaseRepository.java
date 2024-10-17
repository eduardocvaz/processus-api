package dev.eduardovaz.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <Model extends BaseModel> extends JpaRepository<Model, Long> {
    @Query(value = "select e from #{#entityName} e where e.id=:id and e.deletedAt is null")
    Optional<Model> findById(@Param("id") Long id);

    @Query(value = "select e from #{#entityName} e where e.deletedAt is null order by e.id")
    Page<Model> findAll(Pageable pageable);

    @Modifying
    @Query(value = "update #{#entityName} e set e.deletedAt = CURRENT_TIMESTAMP where e.id=:id and e.deletedAt is null")
    void deleteById(@Param("id") Long id);
}
