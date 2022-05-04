package com.netmoor.zenit_task.repository;

import com.netmoor.zenit_task.domain.Executor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Nikolay_Batov on 04.05.2022
 */
@Repository
public interface ExecutorRepository extends JpaRepository<Executor, UUID> {
}
