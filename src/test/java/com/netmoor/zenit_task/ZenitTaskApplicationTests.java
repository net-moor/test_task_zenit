package com.netmoor.zenit_task;

import com.netmoor.zenit_task.domain.Project;
import com.netmoor.zenit_task.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class ZenitTaskApplicationTests extends BaseTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void contextLoads() {
        var project = new Project();
        project.setName("Test");
        projectRepository.save(project);
    }
}
