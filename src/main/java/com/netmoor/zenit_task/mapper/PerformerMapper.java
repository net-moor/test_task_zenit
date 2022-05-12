package com.netmoor.zenit_task.mapper;

import com.netmoor.zenit_task.api.dto.CreatePerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerDto;
import com.netmoor.zenit_task.api.dto.UpdatePerformerDto;
import com.netmoor.zenit_task.domain.Performer;
import org.mapstruct.MappingTarget;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface PerformerMapper extends Mapper<Performer, PerformerDto>{

    Performer mapToEntity(CreatePerformerDto dto);

    Performer mergeDtoToEntity(@MappingTarget Performer performer, UpdatePerformerDto updatePerformerDto);
}
