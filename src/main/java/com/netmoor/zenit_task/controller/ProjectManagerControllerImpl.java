package com.netmoor.zenit_task.controller;

import com.netmoor.zenit_task.api.dto.CreateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectManagerDtoPage;
import com.netmoor.zenit_task.api.rest.ProjectManagerController;
import com.netmoor.zenit_task.mapper.ProjectManagerMapper;
import com.netmoor.zenit_task.service.ProjectManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@RestController
@RequiredArgsConstructor
public class ProjectManagerControllerImpl implements ProjectManagerController {

    private final ProjectManagerService projectManagerService;

    private final ProjectManagerMapper projectManagerMapper;

    @Override
    public ProjectManagerDto findProjectManagerById(UUID projectManagerId) {
        return projectManagerMapper.mapToDto(projectManagerService.findProjectManagerById(projectManagerId));
    }

    @Override
    public ProjectManagerDtoPage getProjectManagerByFilter(ProjectManagerFilterDto projectManagerFilterDto, Pageable pageable) {
        var pageProjectManager = projectManagerService.getProjectManagerByFilter(projectManagerFilterDto, pageable);
        return new ProjectManagerDtoPage(projectManagerMapper.mapToDto(pageProjectManager.getContent()), pageProjectManager.getPageable(), pageProjectManager.getTotalPages());
    }

    @Override
    public ProjectManagerDto createProjectManager(CreateProjectManagerDto createProjectManagerDto) {
        return projectManagerMapper.mapToDto(projectManagerService.createProjectManager(createProjectManagerDto));
    }

    @Override
    public void deleteProjectManager(UUID projectManagerId) {
        projectManagerService.deleteProjectManager(projectManagerId);
    }

    @Override
    public ProjectManagerDto updateProjectManager(UUID projectManagerId, UpdateProjectManagerDto updateProjectManagerDto) {
        return projectManagerMapper.mapToDto(projectManagerService.updateProjectManager(projectManagerId, updateProjectManagerDto));
    }
}
