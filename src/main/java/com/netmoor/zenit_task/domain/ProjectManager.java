package com.netmoor.zenit_task.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class ProjectManager {

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
    @OneToMany
    @JoinColumn(name = "project_manager_id")
    private List<Project> projects;
}