package com.netmoor.zenit_task.api.dto.pageble;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.netmoor.zenit_task.api.dto.ProjectManagerDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
public class ProjectManagerDtoPage extends PageImpl<ProjectManagerDto> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProjectManagerDtoPage(@JsonProperty("content") List<ProjectManagerDto> content, @JsonProperty("number") int number, @JsonProperty("size") int size,
                            @JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                            @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first,
                            @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }
    public ProjectManagerDtoPage(List<ProjectManagerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
