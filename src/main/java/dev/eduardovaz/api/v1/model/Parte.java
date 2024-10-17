package dev.eduardovaz.api.v1.model;

import dev.eduardovaz.api.v1.model.enums.TipoParte;
import dev.eduardovaz.core.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parte extends BaseModel {
    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoParte tipo;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;
}
