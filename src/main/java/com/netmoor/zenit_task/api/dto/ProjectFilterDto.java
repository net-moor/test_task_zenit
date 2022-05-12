package com.netmoor.zenit_task.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@Data
public class ProjectFilterDto {

    private String name;

    private List<ProjectStatusDto> statuses;
}
