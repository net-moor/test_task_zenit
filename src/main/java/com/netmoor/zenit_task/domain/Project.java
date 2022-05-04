package com.netmoor.zenit_task.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Nikolay_Batov on 04.05.2022
 */
@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @NotBlank
    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status = ProjectStatus.NOT_STARTED;

    private Instant startedAt;

    private Instant endedAt;

    @ManyToOne
    private ProjectManager projectManager;

    @ToString.Exclude
    @ManyToMany(mappedBy = "projects")
    private List<Executor> executors = new ArrayList<>();

    public void setExecutors(List<Executor> executors) {
        if (Objects.nonNull(executors)) {
            this.executors.clear();
            this.executors.addAll(executors);
        }
    }
}
