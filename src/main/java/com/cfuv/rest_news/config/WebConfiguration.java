package com.cfuv.rest_news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(cors -> cors.configurationSource(a ->
        {
            var config = new CorsConfiguration();
            config.addAllowedMethod("*");
            config.addAllowedOrigin("/**");
            return config;
        }));

        httpSecurity.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(httpRequest -> {
            httpRequest.requestMatchers(HttpMethod.GET).permitAll();
            httpRequest.requestMatchers(HttpMethod.POST).hasRole("editor");
            httpRequest.requestMatchers(HttpMethod.PUT).hasRole("editor");
            httpRequest.requestMatchers(HttpMethod.DELETE).hasRole("editor");
            httpRequest.anyRequest().denyAll();
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

        var requestJwtTokenFilter = new RequestJwtTokenFilter();
        httpSecurity.addFilterAfter(requestJwtTokenFilter, ExceptionTranslationFilter.class);


        return httpSecurity.build();
    }


}

