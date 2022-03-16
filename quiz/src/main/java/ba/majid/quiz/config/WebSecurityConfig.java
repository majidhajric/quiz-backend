package ba.majid.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.httpBasic().disable().formLogin().disable().csrf().disable().logout().disable();
        http
                .authorizeExchange()
                .pathMatchers("/oauth2-redirect.html",
                        "/swagger-ui.html",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-ui/index.html",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/api/loggedin/confirm/**",
                        "/api/loggedin/confirm/",
                        "/public/oauth2-redirect.html",
                        "/context-path/v3/api-docs",
                        "/context-path/v3/api-docs/**",
                        "/swagger-ui-custom.html",
                        "/webjars/swagger-ui/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/springfox-swagger-ui/**")
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return http.build();
    }

}
