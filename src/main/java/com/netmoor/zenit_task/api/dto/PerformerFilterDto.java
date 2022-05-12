package com.netmoor.zenit_task.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Data
@Schema(description = "Фильтр для поиска исполнителя")
public class PerformerFilterDto {

    @Schema(description = "Имя")
    private String fistName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;
}
