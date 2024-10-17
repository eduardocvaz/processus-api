package dev.eduardovaz.api.v1.model;

import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import dev.eduardovaz.core.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "processos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Processo extends BaseModel {
    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private LocalDate dataAbertura;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProcesso status;

    @ManyToMany
    @JoinTable(
            name = "processo_parte",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "parte_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"processo_id", "parte_id"})
    )
    private List<Parte> partes;
}
