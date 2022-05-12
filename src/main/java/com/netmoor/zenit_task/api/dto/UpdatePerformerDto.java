package com.netmoor.zenit_task.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Data
@Accessors(chain = true)
@Schema(description = "Параметры для редактирования исполнителя")
public class UpdatePerformerDto {

    @Schema(description = "Имя")
    private String fistName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;
}
