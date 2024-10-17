package dev.eduardovaz.api.v1.model;

import dev.eduardovaz.core.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documentos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Documento extends BaseModel {
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "tamanho", nullable = false)
    private Long tamanho;

    @Lob
    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acao_id", nullable = false)
    private Acao acao;
}
