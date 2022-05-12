package com.netmoor.zenit_task;

import com.netmoor.zenit_task.domain.Performer;
import com.netmoor.zenit_task.domain.Project;
import com.netmoor.zenit_task.domain.ProjectManager;
import com.netmoor.zenit_task.repository.PerformerRepository;
import com.netmoor.zenit_task.repository.ProjectManagerRepository;
import com.netmoor.zenit_task.repository.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author Nikolay_Batov on 11.05.2022
 */
public abstract class IntegrationTest extends BaseTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected ProjectManagerRepository projectManagerRepository;

    @Autowired
    protected PerformerRepository performerRepository;

    @AfterEach
    void tearDown() {
        performerRepository.deleteAll();
        projectManagerRepository.deleteAll();
        projectRepository.deleteAll();
    }

    protected Performer createPerformer(boolean persist) {
        return createPerformer(persist, "FistName");
    }

    protected Performer createPerformer(boolean persist, String fistName) {
        return createPerformer(persist, fistName, "LastName");
    }

    protected Performer createPerformer(boolean persist, String fistName, String lastName) {
        return createPerformer(persist, fistName, lastName, "MiddleName");
    }

    protected Performer createPerformer(boolean persist, String fistName, String lastName, String middleName) {
        var performer = new Performer()
                .setFistName(fistName)
                .setLastName(lastName)
                .setMiddleName(middleName);

        if(persist) {
            return performerRepository.save(performer);
        }
        return performer;
    }

    protected ProjectManager createProjectManager(boolean persist) {
        return createProjectManager(persist, "FistName");
    }

    protected ProjectManager createProjectManager(boolean persist, String fistName) {
        return createProjectManager(persist, fistName, "LastName");
    }

    protected ProjectManager createProjectManager(boolean persist, String fistName, String lastName) {
        return createProjectManager(persist, fistName, lastName, "MiddleName");
    }

    protected ProjectManager createProjectManager(boolean persist, String fistName, String lastName, String middleName) {
        var projectManager = new ProjectManager()
                .setFistName(fistName)
                .setLastName(lastName)
                .setMiddleName(middleName);

        if(persist) {
            return projectManagerRepository.save(projectManager);
        }
        return projectManager;
    }

    protected Project createProject(boolean persist) {
        return createProject(persist, "TestProject");
    }

    protected Project createProject(boolean persist, String nameProject) {
        var project = new Project()
                .setName(nameProject);
        if (persist) {
            return projectRepository.save(project);
        }
        return project;
    }
}
