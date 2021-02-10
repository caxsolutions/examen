/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.domain.model.Compras;
import ar.com.plug.examen.domain.model.Estadocompras;
import ar.com.plug.examen.domain.service.ComprasRepository;
import ar.com.plug.examen.domain.service.EstadocomprasRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class EstadocomprasServiceImpl {
	
	@Autowired
	private EstadocomprasRepository estadocomprasRepository;
	
	@Autowired
	private ComprasRepository comprasRepository;
	
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Estadocompras
	 * @return
	 */
	@Transactional
	public Estadocompras saveEstadocompras(Estadocompras estadocompras) throws Exception{
		try {
			
			if(validateData(estadocompras)) {
				
				Optional<Compras> compras = comprasRepository.findById(estadocompras.getCompra().getIdcompra());
				
				if(compras.isPresent()) {
					
					return estadocomprasRepository.save(estadocompras);	
					
				}else {
					throw new Exception("No se encontro ninguna compra con el id " + estadocompras.getCompra().getIdcompra());
				}
			}
			
			return null;
			
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * method that validates the data 
	 * @autor CACP - 8/02/2021
	 * @return
	 */
	private Boolean validateData(Estadocompras estadocompras) throws Exception{
		
		if(estadocompras == null) {
			throw new Exception("El obj estadocompras no puede ser nulo");
		}
		
		if(estadocompras.getCompra() == null) {
			throw new Exception("El obj estadocompras.compras no puede ser nulo");
		}
		if(estadocompras.getEstado() == null) {
			throw new Exception("El obj estadocompras.estado no puede ser nulo");
		}
		if(estadocompras.getFechaestado() == null) {
			throw new Exception("El obj estadocompras.fechacompra no puede ser nulo");
		}
		
		return true;
	}
	
}
