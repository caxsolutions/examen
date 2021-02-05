/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.entity.Estadocompras;
import ar.com.plug.examen.domain.service.EstadocomprasRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class EstadocomprasServiceImpl {
	
	@Autowired
	private EstadocomprasRepository estadocomprasRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Estadocompras
	 * @return
	 */
	public Estadocompras saveEstadocompras(Estadocompras Estadocompras) {
		return estadocomprasRepository.save(Estadocompras);
	}

}
