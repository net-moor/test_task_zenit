package com.netmoor.zenit_task.mapper;

import com.netmoor.zenit_task.api.dto.CreateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectManagerDto;
import com.netmoor.zenit_task.domain.ProjectManager;
import org.mapstruct.MappingTarget;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface ProjectManagerMapper extends Mapper<ProjectManager, ProjectManagerDto> {

    ProjectManager mapToEntity(CreateProjectManagerDto createProjectManagerDto);

    ProjectManager mergeDtoToEntity(@MappingTarget ProjectManager projectManager, UpdateProjectManagerDto updateProjectManagerDto);
}
