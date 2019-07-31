package br.com.iguana.feirafacil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201 = simpleMessage(201, "Recurso criado");
    private final ResponseMessage m204put = simpleMessage(204, "Atalização ok");
    private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("br.com.iguana.feirafacil.controllers"))
                .paths(PathSelectors.any())

                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API do projeto Feirafacil",
                "API com objetivo de documentar os serviços oferecidos." +
                        "URL de acesso local: http://localhost:8080/swagger-ui.html#/",
                "Versão 1.0",
                "",
                new Contact("Hugo iguana", "https://github.com/hugoiguana", "hugo.iguanaa@gmail.com"),
                "Permitido privada",
                "",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}
