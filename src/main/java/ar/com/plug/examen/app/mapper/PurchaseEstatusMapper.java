/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.PurchaseStatusApi;
import ar.com.plug.examen.domain.model.PurchaseStatus;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@Component
public class PurchaseEstatusMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(PurchaseStatus.class, PurchaseStatusApi.class)
		.field("purchase.idpurchase", "idpurchase")
		.byDefault().register();
	}
	
}
