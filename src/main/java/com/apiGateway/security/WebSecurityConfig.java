package com.apiGateway.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {


	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(@NotNull ServerHttpSecurity http) {


		return http
				.exceptionHandling()
				.authenticationEntryPoint((swe, e) -> {
					return Mono.fromRunnable(() -> {
						swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					});
				}).accessDeniedHandler((swe, e) -> {
					return Mono.fromRunnable(() -> {
						swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
					});
				}).and()
				.cors().and()
				.csrf().disable() //csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()).and()
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				//.pathMatchers(HttpMethod.GET, "/**").permitAll()
				//.pathMatchers(HttpMethod.POST, "/**").permitAll()
				//.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.pathMatchers("/login").permitAll()
				//.pathMatchers("/user/save").permitAll() //line added
				//.pathMatchers("/students").permitAll()
				//.pathMatchers("/REGISTRATION-SERVICE/**").permitAll()
				//.pathMatchers("/REGISTRATION-SERVICE/test").permitAll()
				.anyExchange()
				//.permitAll()
				.authenticated()
				
				.and().build();
	}

}
