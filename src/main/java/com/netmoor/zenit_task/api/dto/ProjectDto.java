package com.netmoor.zenit_task.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@Data
@Accessors(chain = true)
public class ProjectDto {

    private UUID id;

    @NotBlank
    private String name;

    private ProjectManagerDto projectManager;

    private ProjectStatusDto status = ProjectStatusDto.NOT_STARTED;

    private Instant startedAt;

    private Instant endedAt;
}
