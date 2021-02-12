/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.PurchaseStatusApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.PurchaseEstatusMapper;
import ar.com.plug.examen.domain.model.Purchases;
import ar.com.plug.examen.domain.model.PurchaseStatus;
import ar.com.plug.examen.domain.service.PurchasesRepository;
import ar.com.plug.examen.domain.service.PurchaseStatusRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class PurchaseEstatusServiceImpl {
	
	@Autowired
	private PurchaseStatusRepository estadocomprasRepository;
	
	@Autowired
	private PurchasesRepository comprasRepository;
	
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param PurchaseStatus
	 * @return
	 */
	@Transactional
	public PurchaseStatusApi saveEstadocompras(PurchaseStatusApi estadocomprasApi) throws Exception{
		try {
			
			Optional<Purchases> compras = comprasRepository.findById(estadocomprasApi.getIdpurchase());
			
			if(compras.isPresent()) {
				
				PurchaseEstatusMapper mapper = new PurchaseEstatusMapper();
				
				PurchaseStatus estadocompras = estadocomprasRepository.save(mapper.map(estadocomprasApi, PurchaseStatus.class));
				
				return mapper.map(estadocompras, PurchaseStatusApi.class); 
				
			}else {
				throw new ExamenException("No purchase was found with the id " + estadocomprasApi.getIdpurchase());
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
}
