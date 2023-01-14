package br.com.projetojupiter.configuration

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfigKotlin {

    @Bean
    open fun springProjetoJupiterOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info().apply {
                title = "Projeto Jupiter"
                description = "Plataforma de cursos online"
                version = "v0.0.2"
            }. license(License().apply {
                name = "Digital House"
                url = "https://www.digitalhouse.com/br"
            }).contact(Contact().apply {
                name = "Grupo Projeto Jupiter"
                email = "jupiterprojeto2@gmail.com"
            })).externalDocs(ExternalDocumentation().apply {
                description = "GitHub"
                url = "https://github.com/DanieleStein"
            })
    }

}