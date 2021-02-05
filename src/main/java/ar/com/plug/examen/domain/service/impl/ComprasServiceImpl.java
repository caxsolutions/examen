/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.entity.Compras;
import ar.com.plug.examen.domain.service.ComprasRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class ComprasServiceImpl {
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Compras
	 * @return
	 */
	public Compras saveCompras(Compras Compras) {
		return comprasRepository.save(Compras);
	}

}
