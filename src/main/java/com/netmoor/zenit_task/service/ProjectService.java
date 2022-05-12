package com.netmoor.zenit_task.service;

import com.netmoor.zenit_task.api.dto.CreateProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectDto;
import com.netmoor.zenit_task.domain.Project;
import com.netmoor.zenit_task.exception.NotFoundEntityException;
import com.netmoor.zenit_task.mapper.ProjectMapper;
import com.netmoor.zenit_task.repository.ProjectRepository;
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
 * @author Nikolay_Batov on 11.05.2022
 */
@Service
@RequiredArgsConstructor
public class ProjectService extends AbstractService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public Project findProjectById(UUID projectId) {
        return projectRepository.findById(projectId).orElseThrow(NotFoundEntityException::new);
    }

    public Page<Project> getProjectByFilter(ProjectFilterDto projectFilterDto, Pageable pageable) {
        List<Specification<Project>> specifications = new ArrayList<>();

        Optional.ofNullable(projectFilterDto.getName()).ifPresent(name -> specifications.add(
                SpecificationUtils.like(name, false, Project.Fields.name)));
        Optional.ofNullable(projectFilterDto.getStatuses()).map(projectMapper::mapStatusDtoToStatusEntity)
                .ifPresent(status -> specifications.add((root, query, builder) -> builder.in(root.get(Project.Fields.status).in(status))));

        return projectRepository.findAll(SpecificationUtils.addFilters(specifications), pageable);
    }

    public Project createProject(CreateProjectDto createProjectDto) {
        Project project = projectMapper.mapToEntity(createProjectDto);
        return projectRepository.save(project);
    }

    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public Project updateProject(UUID projectId, UpdateProjectDto updateProjectDto) {
        Project project = findProjectById(projectId);
        projectMapper.mergeDtoToEntity(project, updateProjectDto);
        return projectRepository.save(project);
    }

    @Transactional
    public Project patchProject(UUID projectId, UpdateProjectDto updateProjectDto) {
        return null;
    }
}
