package com.tumtech.apigateway.config;

import com.netflix.discovery.converters.Auto;
import com.tumtech.apigateway.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtFilter jwtFilter;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig (JwtFilter jwtFilter, UserDetailsService userDetailsService){
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource (){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder (){return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(username -> userDetailsService.loadUserByUsername(username));
        return daoAuthenticationProvider;
    }




    //    @Bean
//    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity httpSecurity){
//        httpSecurity
//                .authorizeExchange()
//                //ALLOWING REGISTER API FOR DIRECT ACCESS
//                .pathMatchers("/api/users/register").permitAll()
//                //ALL OTHER APIS ARE AUTHENTICATED
//                .anyExchange().authenticated()
//                .and()
//                .csrf().disable()
//                .oauth2Login()
//                .and(
//                .oauth2ResourceServer()
//                .jwt();
//        return httpSecurity.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpRequests -> httpRequests
                        .requestMatchers("http").permitAll()
                        .requestMatchers("http").authenticated())
                .logout(logout -> logout.deleteCookies("remove").invalidateHttpSession(true)
             )
//                .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
