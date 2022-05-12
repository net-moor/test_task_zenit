package com.netmoor.zenit_task.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.netmoor.zenit_task.IntegrationTest;
import com.netmoor.zenit_task.api.dto.CreateProjectDto;
import com.netmoor.zenit_task.api.dto.FullProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectStatusDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectDtoPage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Nikolay_Batov on 12.05.2022
 */
public class ProjectControllerTest extends IntegrationTest {

    @Test
    void whenCreateProject_thenSuccess() {

        var reqBody = new CreateProjectDto()
                .setName("TestProject");

        var exchange = testRestTemplate.exchange(
                "/api/v0/project",
                HttpMethod.POST,
                new HttpEntity<>(reqBody),
                FullProjectDto.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertNotNull(exchange.getBody());

        assertNotNull(exchange.getBody().getId());

        var projects = projectRepository.findAll();
        assertEquals(1, projects.size());
    }

    @Test
    void whenDeleteProject_thenSuccess() {
        var project = createProject(true);

        testRestTemplate.delete("/api/v0/project/{projectId}", Map.of("projectId", project.getId().toString()));

        var projects = projectRepository.findAll();
        assertEquals(0, projects.size());
    }

    @Test
    void whenGetProjectById_thenSuccess() {
        var project = createProject(true);

        var response = testRestTemplate.getForEntity(
                "/api/v0/project/{projectId}",
                FullProjectDto.class,
                Map.of("projectId", project.getId().toString()));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(new FullProjectDto()
                .setPerformers(new ArrayList<>())
                .setId(project.getId())
                .setName(project.getName())
                .setStatus(ProjectStatusDto.NOT_STARTED), response.getBody());
    }

    @Test
    void whenUpdateProject_thenSuccess() {
        var project = createProject(true);

        var expected = new FullProjectDto()
                .setPerformers(new ArrayList<>())
                .setId(project.getId())
                .setName("updateName")
                .setStatus(ProjectStatusDto.NOT_STARTED);

        var reqBody = new UpdateProjectDto()
                .setName(expected.getName());

        var response = testRestTemplate.exchange(
                "/api/v0/project/{projectId}",
                HttpMethod.PUT,
                new HttpEntity<>(reqBody),
                FullProjectDto.class,
                Map.of("projectId", project.getId())
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expected, response.getBody());
    }

    @Test
    void whenGetProjectByFilter_thenSuccess() {
        createProject(true, "test1");
        createProject(true, "test2");
        createProject(true, "project");

        var response = testRestTemplate.exchange(
                "/api/v0/project?name={name}",
                HttpMethod.GET,
                null,
                ProjectDtoPage.class,
                Map.of("name", "test")
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getTotalElements());
    }
}
