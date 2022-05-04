package com.netmoor.zenit_task.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
public class Executor {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @NotBlank
    private String fistName;

    @NotBlank
    private String lastName;

    private String middleName;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "project_executor",
            joinColumns = @JoinColumn(name = "executor_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public void setProjects(List<Project> projects) {
        if (Objects.nonNull(projects)) {
            this.projects.clear();
            this.projects.addAll(projects);
        }
    }
}