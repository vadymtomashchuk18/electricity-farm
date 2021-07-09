package com.pexapark.ElectricityFarm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.pexapark.ElectricityFarm"))
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/")
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(Collections.singletonList(securityContext()))
        .tags(new Tag("API endpoints", ""));
  }

  private ApiKey apiKey() {
    return new ApiKey("key", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("key", authorizationScopes));
  }

  @Bean
  springfox.documentation.swagger.web.SecurityConfiguration security() {
    return new springfox.documentation.swagger.web.SecurityConfiguration(
        null, null, null, null, null, ApiKeyVehicle.HEADER, "key", ",");
  }
}
