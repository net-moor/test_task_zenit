package com.netmoor.zenit_task.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FullProjectDto extends ProjectDto {

    private List<PerformerDto> performers;
}
