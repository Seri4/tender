package ru.notSleepAllDay.tender.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:application.properties")
@EnableOpenApi
public class SwaggerConfig {

    private final Environment env;

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.notSleepAllDay.tender.controller"))
                .build()
                .pathMapping("/api/v1");

        if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            docket.pathMapping("/");
        }
        return docket;

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tender-getaway API")
                .description("ru.notSleepAllDay.tender API reference for developers")
                .termsOfServiceUrl("http://example.com")
                .contact(new Contact("Sergey Glebov", "http://localhost:8080", "seri4.gl@gmail.com"))
                .version("1.0")
                .build();
    }


}
