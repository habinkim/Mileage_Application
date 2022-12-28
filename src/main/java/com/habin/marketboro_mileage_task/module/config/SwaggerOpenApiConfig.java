package com.habin.marketboro_mileage_task.module.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static java.time.LocalDate.now;

@Configuration
public class SwaggerOpenApiConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .exposedHeaders("TOKEN");
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(apiInfo()); // API 문서에 대한 정보 추가
    }

    private Info apiInfo() {
        return new Info()
                .title("MarketBoro Mileage Task REST API Documentation (" + now() + ")")
                .description("MarketBoro Mileage Task REST API 명세서")
                .version("0.5");
    }

}
