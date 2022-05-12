package com.netmoor.zenit_task.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@Data
@Accessors(chain = true)
public class UpdateProjectDto {

    @NotBlank
    private String name;
}
