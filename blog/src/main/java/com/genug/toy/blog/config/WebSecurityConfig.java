package com.genug.toy.blog.config;

import com.genug.toy.blog.security.JwtAuthenticationFilter;
import com.genug.toy.blog.security.SampleFilter;
import com.genug.toy.blog.security.SampleFilter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private SampleFilter filter01;

    @Autowired
    private SampleFilter2 filter02;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // matcher는 순서대로 동작
        // 만약 첫번째 모든 요청과 일치하고 두번째 매핑에 도달하지 않기 때문에 유효 안한다.
        /*
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/**")
                .hasRole("USER")
                .and()
                .formLogin();
         */
        /*
        http
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .antMatchers("/**").hasRole("USER")
                )
                .formLogin(withDefaults());
         */
        http.cors()// WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
                .and()
                .csrf().disable() // csrf는 현재 사용하지 않으므로 disable
                .httpBasic().disable() // token을 사용하므로 basic 인증 disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // STATELESS : 무상태
                .and()
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .antMatchers("/", "/auth/**").permitAll()
                                .anyRequest().authenticated()
                );

        // filter 등록. 매 요청마다 CorsFilter 실행한 후에 jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
//        http.addFilter(filter03);
//        http.addFilterAfter(filter02, CorsFilter.class);
//        http.addFilterAfter(filter01, SampleFilter2.class);

        return http.build();
    }

    /*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(HttpMethod.POST, "/auth")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and().ignoring()
                .antMatchers(HttpMethod.GET, "/");
    }
     */

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("{noop}1234")
//                .roles("ADMIN", "USER")
//                .build();
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}