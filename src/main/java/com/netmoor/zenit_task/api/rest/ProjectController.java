package com.netmoor.zenit_task.api.rest;

import static com.netmoor.zenit_task.api.rest.UrlConstants.API_V0;

import com.netmoor.zenit_task.api.dto.CreateProjectDto;
import com.netmoor.zenit_task.api.dto.FullProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectDto;
import com.netmoor.zenit_task.api.dto.ProjectFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectDtoPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
@Tags(
        value = {
                @Tag(name = "API Project", description = "Ресурсы управления проектом")
        }
)
@RequestMapping(API_V0 + "/project")
public interface ProjectController {

    @Operation(summary = "Поиск проекта по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FullProjectDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping("/{projectId}")
    FullProjectDto findProjectById(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectId);

    @Operation(summary = "Пагенированый поиск проекта используя фильтр")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProjectDtoPage.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping
    Page<ProjectDto> getProjectByFilter(@ParameterObject ProjectFilterDto projectFilterDto, @ParameterObject Pageable pageable);

    @Operation(summary = "Добавление проекта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FullProjectDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PostMapping
    FullProjectDto createProject(@RequestBody @Valid CreateProjectDto createProjectDto);

    @Operation(summary = "Удаление проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @DeleteMapping("/{projectId}")
    void deleteProject(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectId);

    @Operation(summary = "Редактирование проекта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FullProjectDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PutMapping("/{projectId}")
    FullProjectDto updateProject(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectId,
                                           @RequestBody UpdateProjectDto updateProjectDto);
}
