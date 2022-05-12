package com.netmoor.zenit_task.mapper;

import com.netmoor.zenit_task.api.dto.CreateProjectDto;
import com.netmoor.zenit_task.api.dto.FullProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectStatusDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectDto;
import com.netmoor.zenit_task.domain.Project;
import com.netmoor.zenit_task.domain.ProjectStatus;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface ProjectMapper extends Mapper<Project, FullProjectDto> {

    @Named(value = "shortProjectDto")
    ProjectDto mapToShortDto(Project entity);

    @IterableMapping(qualifiedByName = "shortProjectDto")
    List<ProjectDto> mapToShortDto(List<Project> entity);

    ProjectStatus mapStatusDtoToStatusEntity(ProjectStatusDto projectStatusDtos);

    List<ProjectStatus> mapStatusDtoToStatusEntity(List<ProjectStatusDto> projectStatusDtos);

    Project mapToEntity(CreateProjectDto createProjectDto);

    Project mergeDtoToEntity(@MappingTarget Project project, UpdateProjectDto updateProjectDto);
}
