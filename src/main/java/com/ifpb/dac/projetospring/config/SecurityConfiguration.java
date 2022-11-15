package com.ifpb.dac.projetospring.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ifpb.dac.projetospring.business.service.PasswordEncoderService;
import com.ifpb.dac.projetospring.business.service.SystemRoleService;
import com.ifpb.dac.projetospring.business.service.TokenService;
import com.ifpb.dac.projetospring.business.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public TokenFilter jwtTokenFilter() {
        return new TokenFilter(tokenService, userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoderService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/isTokenValid").permitAll()

                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())

                .antMatchers(HttpMethod.PUT, "/api/person/associateVehicle")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/person/update/cpf={cpf}")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/person/delete/cpf={cpf}")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())

                .antMatchers(HttpMethod.PUT, "/api/vehicle/update/plate={plate}")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/vehicle/delete/plate={plate}")
                    .hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())

                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.logout(logout -> logout
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                            Authentication authentication)
                            throws IOException, ServletException {
                        // Don't do anything on logout
                    }
                }));
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        List<String> all = Arrays.asList("*");

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(all);
        corsConfiguration.setAllowedOriginPatterns(all);
        corsConfiguration.setAllowedHeaders(all);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(configSource);

        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

}
