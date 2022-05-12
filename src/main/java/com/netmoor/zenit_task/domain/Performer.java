package com.netmoor.zenit_task.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
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
@FieldNameConstants
@Accessors(chain = true)
public class Performer {

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
    @JoinTable(name = "project_performer",
            joinColumns = @JoinColumn(name = "performer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public void setProjects(List<Project> projects) {
        if (Objects.nonNull(projects)) {
            this.projects.clear();
            this.projects.addAll(projects);
        }
    }
}