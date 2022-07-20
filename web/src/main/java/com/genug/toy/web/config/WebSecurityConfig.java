package com.genug.toy.web.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf()
                .disable()
            .httpBasic()
                .disable()
            .sessionManagement() // session 기반이 아님을 선언
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 무상태
            .and()
            .authorizeRequests()
                .antMatchers("/", "/auth/**").permitAll()
            .anyRequest()
                .authenticated();

        // TODO -> null 에 JWT필터 추가하기
        // 뒤에 있는 CorsFilter를 실행한 후에 jwt인증필터 실행한다.
        http.addFilterAfter(
                null,
                CorsFilter.class
        );

        return http.build();
    }

}
