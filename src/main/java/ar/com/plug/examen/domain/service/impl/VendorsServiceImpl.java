/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.api.VendorsApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.VendorsMapper;
import ar.com.plug.examen.domain.model.Vendors;
import ar.com.plug.examen.domain.service.VendorsRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class VendorsServiceImpl {
	
	@Autowired
	private VendorsRepository vendedoresRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Vendors
	 * @return
	 */
	@Transactional
	public VendorsApi saveVendors(VendorsApi vendedoresApi) throws Exception{

		VendorsMapper mapper = new VendorsMapper();
			
		Vendors vendedores = mapper.map(vendedoresApi, Vendors.class);
		
		Vendors vendedores2 = vendedoresRepository.save(vendedores);
		
		return mapper.map(vendedores2, VendorsApi.class);
	}

	/**
	 * method that find all entity 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<VendorsApi> getVendors() {
		VendorsMapper mapper = new VendorsMapper();
		
		List<Vendors> vendedoresList =  vendedoresRepository.findAll();
		
		return mapper.mapAsList(vendedoresList, VendorsApi.class);
	}

	/**
	 * method that find entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public VendorsApi getVendorsById(Integer idVendedores) {
		VendorsMapper mapper = new VendorsMapper();
		Vendors vendedores = vendedoresRepository.findById(idVendedores).orElse(null);
		return mapper.map(vendedores, VendorsApi.class);
	}

	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param vendedores
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public VendorsApi updateVendors(VendorsApi vendedores) throws Exception {
		
		VendorsMapper mapper = new VendorsMapper();
		
		Vendors vendedoresEn = vendedoresRepository.findById(vendedores.getIdseller()).orElse(null);
		
		if(vendedoresEn != null && vendedoresEn.getIdseller() != null) {
			
			vendedoresEn.setCode(vendedores.getCode());
			vendedoresEn.setName(vendedores.getName());
			vendedoresEn.setEnabled(vendedores.getEnabled());
		}
		
		return mapper.map(vendedoresRepository.save(vendedoresEn), VendorsApi.class);
	}
	
	/**
	 * method that delete entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	@Transactional
	public MessageApi deleteVendorsById(Integer idVendedores) throws Exception{
		
		if(idVendedores == null) {
			throw new ExamenException("The vendorid field cannot be null.");
		}
		
		MessageApi api = new MessageApi();
		
		Vendors vendedor = vendedoresRepository.findById(idVendedores).orElse(null);
		
		if(vendedor != null && vendedor.getIdseller() != null) {
			
			vendedoresRepository.deleteById(idVendedores);
			
			api.setMessage("Seller successfully removed.");
			
		}else {
			api.setMessage("Seller with id " + idVendedores + " Not found in the system.");
		}
		
		return api;
	}
}
