/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.ClientsApi;
import ar.com.plug.examen.domain.model.Clients;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@Component
public class ClientsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Clients.class, ClientsApi.class).byDefault().register();
	}
	
}
