package com.netmoor.zenit_task.service;

import com.netmoor.zenit_task.api.dto.CreateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectManagerDto;
import com.netmoor.zenit_task.domain.ProjectManager;
import com.netmoor.zenit_task.exception.NotFoundEntityException;
import com.netmoor.zenit_task.mapper.ProjectManagerMapper;
import com.netmoor.zenit_task.repository.ProjectManagerRepository;
import com.netmoor.zenit_task.repository.SpecificationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@Service
@RequiredArgsConstructor
public class ProjectManagerService extends AbstractService {

    private final ProjectManagerMapper projectManagerMapper;

    private final ProjectManagerRepository projectManagerRepository;

    public ProjectManager findProjectManagerById(UUID projectManagerId) {
        return projectManagerRepository.findById(projectManagerId).orElseThrow(NotFoundEntityException::new);
    }

    public Page<ProjectManager> getProjectManagerByFilter(ProjectManagerFilterDto projectManagerFilter, Pageable pageable) {
        List<Specification<ProjectManager>> specifications = new ArrayList<>();

        Optional.ofNullable(projectManagerFilter.getFistName()).ifPresent(fistName -> specifications.add(
                SpecificationUtils.like(fistName, false, ProjectManager.Fields.fistName)));
        Optional.ofNullable(projectManagerFilter.getFistName()).ifPresent(lastName -> specifications.add(
                SpecificationUtils.like(lastName, false, ProjectManager.Fields.lastName)));
        Optional.ofNullable(projectManagerFilter.getMiddleName()).ifPresent(middleName -> specifications.add(
                SpecificationUtils.like(middleName, false, ProjectManager.Fields.middleName)));

        return projectManagerRepository.findAll(SpecificationUtils.addFilters(specifications), pageable);
    }

    public ProjectManager createProjectManager(CreateProjectManagerDto createProjectManagerDto) {
        return projectManagerRepository.save(projectManagerMapper.mapToEntity(createProjectManagerDto));
    }

    public void deleteProjectManager(UUID projectManagerId) {
        projectManagerRepository.deleteById(projectManagerId);
    }

    @Transactional
    public ProjectManager updateProjectManager(UUID projectManagerId, UpdateProjectManagerDto updateProjectManagerDto) {
        var projectManager = findProjectManagerById(projectManagerId);
        projectManagerMapper.mergeDtoToEntity(projectManager, updateProjectManagerDto);
        return projectManagerRepository.save(projectManager);
    }

    public ProjectManager patchProjectManager(UUID projectManagerId, UpdateProjectManagerDto updateProjectManagerDto) {
        var projectManager = findProjectManagerById(projectManagerId);
        pathField(updateProjectManagerDto.getFistName(), projectManager::setFistName);
        pathField(updateProjectManagerDto.getLastName(), projectManager::setLastName);
        pathField(updateProjectManagerDto.getMiddleName(), projectManager::setMiddleName);
        return projectManagerRepository.save(projectManager);
    }
}
