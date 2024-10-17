package dev.eduardovaz.api.v1.specification;

import dev.eduardovaz.api.v1.model.Parte;
import dev.eduardovaz.api.v1.model.Processo;
import dev.eduardovaz.api.v1.model.enums.StatusProcesso;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoSpecification {

    public static Specification<Processo> comId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Processo> comCpfCnpjParte(String cpfCnpj) {
        return (root, query, criteriaBuilder) -> { Join<Processo, Parte> partesJoin = root.join("partes");return criteriaBuilder.equal(partesJoin.get("cpfCnpj"), cpfCnpj);};
    }

    public static Specification<Processo> comStatus(StatusProcesso status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Processo> comDataAberturaMenorQue(LocalDate data) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("dataAbertura"), data);
    }

    public static Specification<Processo> comDataAberturaEntre(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataAbertura"), dataInicio, dataFim);
    }

    public static Specification<Processo> buscarProcessos(Long id, StatusProcesso status, LocalDate dataInicio, LocalDate dataFim, String cpfCnpj) {
        return (root, query, criteriaBuilder) -> { List<Predicate> predicates = new ArrayList<>();
            if (id != null) {
                predicates.add(comId(id).toPredicate(root, query, criteriaBuilder));
            }
            if (status != null) {
                predicates.add(comStatus(status).toPredicate(root, query, criteriaBuilder));
            }
            if (cpfCnpj != null) {
                predicates.add(comCpfCnpjParte(cpfCnpj).toPredicate(root, query, criteriaBuilder));
            }
            if (dataInicio != null && dataFim != null) {
                predicates.add(comDataAberturaEntre(dataInicio, dataFim).toPredicate(root, query, criteriaBuilder));
            } else if (dataInicio != null) {
                predicates.add(comDataAberturaMenorQue(dataInicio).toPredicate(root, query, criteriaBuilder));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
