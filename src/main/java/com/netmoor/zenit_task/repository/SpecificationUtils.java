package com.netmoor.zenit_task.repository;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@UtilityClass
public class SpecificationUtils {

    public <T> Specification<T> findAll() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public <T> Specification<T> like(String value, boolean fromBeginning, String fieldName) {
        String pattern = (fromBeginning ? "" : "%") + value.toLowerCase() + "%";
        return (root, query, builder) -> builder.like(builder.lower(root.get(fieldName)), pattern);
    }

    public <T> Specification<T> in(List<T> value, String fieldName) {
        return (root, query, builder) -> builder.in(root.get(fieldName).in(value));
    }

    public <T> Specification<T> addFilters(List<Specification<T>> specifications) {
        Specification<T> spec = null;
        if (specifications.isEmpty()) {
            spec = Specification.where(findAll());
        } else {
            for (Specification<T> next : specifications) {
                if (Objects.isNull(spec)) {
                    spec = Specification.where(next);
                } else {
                    spec = spec.and(next);
                }
            }
        }
        return spec;
    }
}
