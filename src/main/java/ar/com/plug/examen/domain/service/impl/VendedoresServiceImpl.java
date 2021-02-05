/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.entity.Vendedores;
import ar.com.plug.examen.domain.service.VendedoresRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class VendedoresServiceImpl {
	
	@Autowired
	private VendedoresRepository vendedoresRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Vendedores
	 * @return
	 */
	public Vendedores saveVendedores(Vendedores Vendedores) {
		return vendedoresRepository.save(Vendedores);
	}

}
