package com.netmoor.zenit_task.controller;

import com.netmoor.zenit_task.api.dto.CreatePerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdatePerformerDto;
import com.netmoor.zenit_task.api.dto.pageble.PerformerDtoPage;
import com.netmoor.zenit_task.api.rest.PerformerController;
import com.netmoor.zenit_task.mapper.PerformerMapper;
import com.netmoor.zenit_task.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@RestController
@RequiredArgsConstructor
public class PerformerControllerImpl implements PerformerController {

    private final PerformerService performerService;
    private final PerformerMapper performerMapper;

    @Override
    public PerformerDto getPerformerById(UUID performerId) {
        return performerMapper.mapToDto(performerService.findPerformerById(performerId));
    }

    @Override
    public Page<PerformerDto> getPerformerByFilter(PerformerFilterDto performerFilter, Pageable pageable) {
        var pagePerformer = performerService.findAllByFilter(performerFilter, pageable);
        return new PerformerDtoPage(
                performerMapper.mapToDto(pagePerformer.getContent()),
                pagePerformer.getPageable(),
                pagePerformer.getTotalElements()
        );
    }

    @Override
    public PerformerDto createPerformer(CreatePerformerDto createPerformerDto) {
        return performerMapper.mapToDto(performerService.createPerformer(createPerformerDto));
    }

    @Override
    public void deletePerformer(UUID performerId) {
        performerService.deletePerformerById(performerId);
    }

    @Override
    public PerformerDto updatePerformer(UUID performerId, UpdatePerformerDto updatePerformerDto) {
        return performerMapper.mapToDto(performerService.updatePerformer(performerId, updatePerformerDto));
    }
}
