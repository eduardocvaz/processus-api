package dev.eduardovaz.api.v1.model;

import dev.eduardovaz.api.v1.model.enums.TipoAcao;
import dev.eduardovaz.core.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "acoes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Acao extends BaseModel {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao tipo;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Column( length = 500)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    @OneToMany(mappedBy = "acao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;
}
