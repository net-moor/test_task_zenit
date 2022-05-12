package com.netmoor.zenit_task.api.rest;

import static com.netmoor.zenit_task.api.rest.UrlConstants.API_V0;

import com.netmoor.zenit_task.api.dto.CreateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerDto;
import com.netmoor.zenit_task.api.dto.ProjectManagerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdateProjectManagerDto;
import com.netmoor.zenit_task.api.dto.pageble.ProjectManagerDtoPage;
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
                @Tag(name = "API Project Manager", description = "Ресурсы управления руководителя проекта")
        }
)
@RequestMapping(API_V0 + "/project/manager")
public interface ProjectManagerController {

    @Operation(summary = "Поиск руководителя проекта по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProjectManagerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping("/{projectManagerId}")
    ProjectManagerDto findProjectManagerById(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectManagerId);

    @Operation(summary = "Пагенированый поиск руководителя проекта используя фильтр")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProjectManagerDtoPage.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping
    Page<ProjectManagerDto> getProjectManagerByFilter(@ParameterObject ProjectManagerFilterDto projectManagerFilterDto, @ParameterObject Pageable pageable);

    @Operation(summary = "Добавление руководителя проекта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProjectManagerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PostMapping
    ProjectManagerDto createProjectManager(@RequestBody @Valid CreateProjectManagerDto createProjectManagerDto);

    @Operation(summary = "Удаление руководителя проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @DeleteMapping("/{projectManagerId}")
    void deleteProjectManager(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectManagerId);

    @Operation(summary = "Редактирование руководителя проекта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProjectManagerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PutMapping("/{projectManagerId}")
    ProjectManagerDto updateProjectManager(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID projectManagerId,
                                        @RequestBody UpdateProjectManagerDto updateProjectManagerDto);
}
