package com.netmoor.zenit_task.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Data
@Accessors(chain = true)
@Schema(description = "Исполнитель")
public class PerformerDto {

    @NotNull
    @Schema(description = "идентификатор")
    private UUID id;

    @NotBlank
    @Schema(description = "Имя")
    private String fistName;

    @NotBlank
    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;
}
