package com.netmoor.zenit_task.controller;

import com.netmoor.zenit_task.api.dto.CreateProjectDto;
import com.netmoor.zenit_task.api.dto.FullProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectDtoPage;
import com.netmoor.zenit_task.api.rest.ProjectController;
import com.netmoor.zenit_task.mapper.ProjectMapper;
import com.netmoor.zenit_task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Nikolay_Batov on 11.05.2022
 */
@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    @Override
    public FullProjectDto findProjectById(UUID projectId) {
        return projectMapper.mapToDto(projectService.findProjectById(projectId));
    }

    @Override
    public Page<ProjectDto> getProjectByFilter(ProjectFilterDto projectFilterDto, Pageable pageable) {
        var projectPage = projectService.getProjectByFilter(projectFilterDto, pageable);
        return new ProjectDtoPage(projectMapper.mapToShortDto(projectPage.getContent()), projectPage.getPageable(), projectPage.getTotalElements());
    }

    @Override
    public FullProjectDto createProject(CreateProjectDto createProjectDto) {
        return projectMapper.mapToDto(projectService.createProject(createProjectDto));
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectService.deleteProject(projectId);
    }

    @Override
    public FullProjectDto updateProject(UUID projectId, UpdateProjectDto updateProjectDto) {
        return projectMapper.mapToDto(projectService.updateProject(projectId, updateProjectDto));
    }
}
