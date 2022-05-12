package com.netmoor.zenit_task.repository;

import com.netmoor.zenit_task.domain.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Nikolay_Batov on 04.05.2022
 */
@Repository
public interface PerformerRepository extends JpaRepository<Performer, UUID>, JpaSpecificationExecutor<Performer> {
}
