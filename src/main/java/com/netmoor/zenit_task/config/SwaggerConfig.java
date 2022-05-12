package com.netmoor.zenit_task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Тестовое задание банка Зенит",
                version = "1.0",
                license = @License(
                        name = "Все права защищены"
                ),
                contact = @Contact(
                        name = "Зенит",
                        url = "https://www.zenit.ru",
                        email = "info@zenit.ru"
                )
        )
)
public class SwaggerConfig {
}
