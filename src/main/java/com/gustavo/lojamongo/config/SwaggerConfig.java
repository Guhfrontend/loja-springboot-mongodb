package com.gustavo.lojamongo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springLojaMongoOpenAPI(){
        return new OpenAPI().info(new Info().title("Projeto webservice loja")
                .description("Projeto com banco não relacional - MongoDB").version("v0.0.1")
                .license(new License().name("Gustavo Ribeiro").url("https://portifolio-nine-rust.vercel.app"))
                .contact(new Contact().name("Gustavo Ribeiro da Silva").url("https://www.linkedin.com/in/gustavo-r13/").email("gustavo.rsilva08@gmail.com")))
                .externalDocs(new ExternalDocumentation().description("Github").url("https://github.com/Guhfrontend"));
    }

    @Bean
    OpenApiCustomizer customizer(){
        return openAPI -> {
            openAPI.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Pesistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
                apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
            }));
        };
    }


    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

}
