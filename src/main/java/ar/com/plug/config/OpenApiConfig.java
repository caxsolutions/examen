/**
 * @autor CACP - 10/02/2021
 */
package ar.com.plug.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @autor luxos CACP - 10/02/2021
 *
 */
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customConfiguration() {
		
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Carlos Polanco Examen -  FlexibilitySRL")
						.version ("1.0")
						.description("API - Examen Productos, Clientes, Vendedores y Compras")
						.contact(new io.swagger.v3.oas.models.info.Contact().email("carlos.polanco010@gmail.com")));
	}
}
