/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.PurchasesApi;
import ar.com.plug.examen.app.mapper.PurchasesMapper;
import ar.com.plug.examen.domain.model.PurchaseStatus;
import ar.com.plug.examen.domain.model.Purchases;
import ar.com.plug.examen.domain.service.PurchaseStatusRepository;
import ar.com.plug.examen.domain.service.PurchasesRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class PurchasesServiceImpl {
	
	@Autowired
	private PurchasesRepository comprasRepository;
	@Autowired
	private PurchaseStatusRepository estadocomprasRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Purchases
	 * @return
	 */
	@Transactional
	public PurchasesApi savePurchase(PurchasesApi comprasApi) throws Exception{
		
		PurchasesMapper mapper = new PurchasesMapper();
		
		Purchases compras = mapper.map(comprasApi, Purchases.class);
		
		Purchases comprasSave = comprasRepository.save(compras);
		
		//register state
		PurchaseStatus estadocompras = new PurchaseStatus();
		estadocompras.setPurchase(comprasSave);
		estadocompras.setState(1);
		estadocompras.setStatedate(comprasSave.getPurchasedate());
		
		estadocomprasRepository.save(estadocompras);
		
		return mapper.map(comprasSave, PurchasesApi.class);
	}

	/**
	 * method that find
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<PurchasesApi> getPurchases() {
		
		PurchasesMapper mapper = new PurchasesMapper();
		
		return mapper.mapAsList(comprasRepository.findAll(), PurchasesApi.class);
	}

	/**
	 * method that find compras by id
	 * @autor CACP - 5/02/2021
	 * @param idcompra
	 * @return
	 */
	public PurchasesApi getPurchasesById(Integer idCompra) {
		PurchasesMapper mapper = new PurchasesMapper();
		Purchases compras = comprasRepository.findById(idCompra).orElse(null);
		return mapper.map(compras, PurchasesApi.class); 
	}
}
