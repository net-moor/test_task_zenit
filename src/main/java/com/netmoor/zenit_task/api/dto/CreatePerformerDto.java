package com.netmoor.zenit_task.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Data
@Accessors(chain = true)
@Schema(description = "Параметры для добавления исполнителя")
public class CreatePerformerDto {

    @Schema(description = "Имя")
    @NotBlank
    private String fistName;

    @Schema(description = "Фамилия")
    @NotBlank
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;
}