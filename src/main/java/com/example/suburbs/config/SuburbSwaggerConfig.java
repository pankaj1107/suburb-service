package com.example.suburbs.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is used to provide configuration like SWAGGER and others to built
 * up REST API clients.
 *
 * @author pankaj.yadav
 */
@EnableSwagger2
@Configuration
public class SuburbSwaggerConfig {

	/**
	 * Configure the API info for the swagger UI.
	 *
	 * @param environment environment details
	 * @return Swagger API info of the service.
	 */
	private ApiInfo apiInfo(final Environment environment) {
		return new ApiInfoBuilder().title("Suburb Rest API")
				.description("This page contains API definitions for Suburb service.")
				.contact(new Contact("Suburbs API", "https://localhost:9001/api/v1/suburbs/", "")).build();
	}

	/**
	 * Constructs Docket for swagger API.
	 *
	 * @param environment environment details
	 * @return docket
	 */
	@Bean
	public Docket api(final Environment environment) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.suburbs")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo(environment)).pathMapping("/").directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false);

	}

	/**
	 * Sets the CORS filter.
	 *
	 * @return cors filter
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Authorization", "Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
