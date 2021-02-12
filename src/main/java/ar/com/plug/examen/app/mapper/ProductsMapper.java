/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.ProductsApi;
import ar.com.plug.examen.domain.model.Products;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@Component
public class ProductsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Products.class, ProductsApi.class).byDefault().register();
	}
	
}
