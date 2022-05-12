package com.netmoor.zenit_task.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.netmoor.zenit_task.IntegrationTest;
import com.netmoor.zenit_task.api.dto.CreateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectManagerDtoPage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author Nikolay_Batov on 12.05.2022
 */
public class ProjectManagerControllerTest extends IntegrationTest {

    @Test
    void whenCreateProjectManager_thenSuccess() {
        var req = new CreateProjectManagerDto()
                .setFistName("testFistName")
                .setLastName("testLastName")
                .setMiddleName("testMiddleName");

        var response = testRestTemplate.exchange(
                "/api/v0/project/manager",
                HttpMethod.POST,
                new HttpEntity<>(req),
                ProjectManagerDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertNotNull(response.getBody().getId());

        var projectManagers = projectManagerRepository.findAll();
        assertEquals(1, projectManagers.size());
    }

    @Test
    void whenDeleteProjectManager_thenSuccess() {
        var projectManager = createProjectManager(true);

        testRestTemplate.delete("/api/v0/project/manager/{projectManagerId}", Map.of("projectManagerId", projectManager.getId().toString()));

        var projectManagers = projectManagerRepository.findAll();
        assertEquals(0, projectManagers.size());
    }

    @Test
    void whenGetProjectManagerById_thenSuccess() {
        var projectManager = createProjectManager(true);

        var response = testRestTemplate.getForEntity(
                "/api/v0/project/manager/{projectManagerId}",
                ProjectManagerDto.class,
                Map.of("projectManagerId", projectManager.getId().toString()));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(new ProjectManagerDto()
                .setId(projectManager.getId())
                .setFistName(projectManager.getFistName())
                .setLastName(projectManager.getLastName())
                .setMiddleName(projectManager.getMiddleName()), response.getBody());
    }

    @Test
    void whenUpdateProjectManager_thenSuccess() {
        var projectManager = createProjectManager(true);

        var expected = new ProjectManagerDto()
                .setId(projectManager.getId())
                .setFistName("updateFistName")
                .setLastName("updateLastName")
                .setMiddleName("updateMiddleName");

        var body = new UpdateProjectManagerDto()
                .setFistName(expected.getFistName())
                .setLastName(expected.getLastName())
                .setMiddleName(expected.getMiddleName());

        var response = testRestTemplate.exchange(
                "/api/v0/project/manager/{projectManagerId}",
                HttpMethod.PUT,
                new HttpEntity<>(body),
                ProjectManagerDto.class,
                Map.of("projectManagerId", projectManager.getId())
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expected, response.getBody());
    }

    @Test
    void whenGetPProjectManagerByFilter_thenSuccess() {
        createProjectManager(true, "name1");
        createProjectManager(true, "name2", "lastName2");
        createProjectManager(true, "fistName3");

        var response = testRestTemplate.exchange(
                "/api/v0/project/manager?fistName={fistName}&lastName={lastName}",
                HttpMethod.GET,
                null,
                ProjectManagerDtoPage.class,
                Map.of("fistName", "name", "lastName", "lastName2")
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }
}
