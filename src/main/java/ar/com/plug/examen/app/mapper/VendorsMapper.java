/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.VendorsApi;
import ar.com.plug.examen.domain.model.Vendors;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@Component
public class VendorsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Vendors.class, VendorsApi.class).byDefault().register();
	}
	
}
