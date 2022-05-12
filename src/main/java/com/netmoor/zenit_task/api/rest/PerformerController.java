package com.netmoor.zenit_task.api.rest;

import static com.netmoor.zenit_task.api.rest.UrlConstants.API_V0;

import com.netmoor.zenit_task.api.dto.CreatePerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerFilterDto;
import com.netmoor.zenit_task.api.dto.UpdatePerformerDto;
import com.netmoor.zenit_task.api.dto.pageble.PerformerDtoPage;
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
 * @author Nikolay_Batov on 05.05.2022
 */
@Tags(
        value = {
                @Tag(name = "API Performers", description = "Ресурсы управления исполнителями")
        }
)
@RequestMapping(API_V0 + "/performer")
public interface PerformerController {


    @Operation(summary = "Поиск исполнителя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PerformerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping("/{performerId}")
    PerformerDto getPerformerById(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID performerId);

    @Operation(summary = "Пагенированый поиск исполнителей используя фильтр")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PerformerDtoPage.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @GetMapping
    Page<PerformerDto> getPerformerByFilter(@ParameterObject PerformerFilterDto performerFilter, @ParameterObject Pageable pageable);

    @Operation(summary = "Добавление исполнителя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PerformerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PostMapping
    PerformerDto createPerformer(@RequestBody @Valid CreatePerformerDto createPerformerDto);

    @Operation(summary = "Удаление исполнителя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @DeleteMapping("/{performerId}")
    void deletePerformer(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID performerId);

    @Operation(summary = "Редактирование исполнителя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PerformerDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Сущность не найдена", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)}),
            @ApiResponse(responseCode = "422", description = "Невозможно обработать запрос", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})
    })
    @PutMapping("/{performerId}")
    PerformerDto updatePerformer(@PathVariable @Schema(example = "daef4154-f1b8-4edc-bc90-3358c4fecf49") UUID performerId,
                                 @RequestBody UpdatePerformerDto updatePerformerDto);
}
