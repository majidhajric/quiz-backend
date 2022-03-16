package ba.majid.quiz.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${springdoc.swagger-ui.oauth.clientId}")
    public String CLIENT_ID = "swagger-ui-quiz";

    @Value("${springdoc.swagger-ui.oauth.clientId}")
    public String CLIENT_SECRET="";

    @Value("${springdoc.oAuthFlow.authorizationUrl}")
    public String authUrl="";

    @Value("${springdoc.oAuthFlow.tokenUrl}")
    public String tokenUrl="";

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .securitySchemes(List.of(securityScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(getApiInfo());

    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(tokenEndpointBuilder -> {
                    tokenEndpointBuilder
                            .url(tokenUrl)
                            .tokenName("oauthtoken")
                            .build();
                })
                .tokenRequestEndpoint(tokenRequestEndpointBuilder -> {
                    tokenRequestEndpointBuilder.url(authUrl)
                            .clientIdName(CLIENT_ID)
                            .clientSecretName(CLIENT_SECRET)
                            .build();
                }).build();

        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(List.of(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Quiz Service",
                "",
                "1.0.0",
                "",
                null,
                "",
                "",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(
                        List.of(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "read access for application"),
                new AuthorizationScope("write", "write access for application"),
        };
    }
}
