package com.netmoor.zenit_task.service;

import com.netmoor.zenit_task.api.dto.CreatePerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdatePerformerDto;
import com.netmoor.zenit_task.domain.Performer;
import com.netmoor.zenit_task.exception.NotFoundEntityException;
import com.netmoor.zenit_task.mapper.PerformerMapper;
import com.netmoor.zenit_task.repository.PerformerRepository;
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
 * @author Nikolay_Batov on 05.05.2022
 */
@Service
@RequiredArgsConstructor
public class PerformerService extends AbstractService {

    private final PerformerRepository performerRepository;
    private final PerformerMapper performerMapper;

    public Performer createPerformer(CreatePerformerDto performerDto) {
        var performer = performerMapper.mapToEntity(performerDto);
        return performerRepository.save(performer);
    }

    public Page<Performer> findAllByFilter(PerformerFilterDto performerFilter, Pageable pageable) {
        List<Specification<Performer>> specifications = new ArrayList<>();

        Optional.ofNullable(performerFilter.getFistName()).ifPresent(fistName -> specifications.add(
                SpecificationUtils.like(fistName, false, Performer.Fields.fistName)));
        Optional.ofNullable(performerFilter.getLastName()).ifPresent(lastName -> specifications.add(
                SpecificationUtils.like(lastName, false, Performer.Fields.lastName)));
        Optional.ofNullable(performerFilter.getMiddleName()).ifPresent(middleName -> specifications.add(
                SpecificationUtils.like(middleName, false, Performer.Fields.middleName)));

        return performerRepository.findAll(SpecificationUtils.addFilters(specifications), pageable);
    }

    public Performer findPerformerById(UUID performerId) {
        return performerRepository.findById(performerId).orElseThrow(NotFoundEntityException::new);
    }

    public void deletePerformerById(UUID performerId) {
        performerRepository.deleteById(performerId);
    }

    @Transactional
    public Performer updatePerformer(UUID performerId, UpdatePerformerDto updatePerformerDto) {
        var performer = findPerformerById(performerId);
        performerMapper.mergeDtoToEntity(performer, updatePerformerDto);
        return performerRepository.save(performer);
    }

    @Transactional
    public Performer patchPerformer(UUID performerId, UpdatePerformerDto updatePerformerDto) {
        var performer = findPerformerById(performerId);
        pathField(updatePerformerDto.getFistName(), performer::setFistName);
        pathField(updatePerformerDto.getLastName(), performer::setLastName);
        pathField(updatePerformerDto.getMiddleName(), performer::setMiddleName);
        return performerRepository.save(performer);
    }
}
