/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

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
public class ComprasServiceImpl {
	
	@Autowired
	private ComprasRepository comprasRepository;
	@Autowired
	private EstadocomprasRepository estadocomprasRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Compras
	 * @return
	 */
	@Transactional
	public Compras saveCompras(Compras Compras) {
		
		Compras comprasSave = comprasRepository.save(Compras);
		
		//register state
		Estadocompras estadocompras = new Estadocompras();
		estadocompras.setCompra(comprasSave);
		estadocompras.setEstado(1);
		estadocompras.setFechaestado(comprasSave.getFechacompra());
		
		estadocomprasRepository.save(estadocompras);
		
		return comprasSave;
	}

	/**
	 * method that find
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<Compras> getCompras() {
		return comprasRepository.findAll();
	}

	/**
	 * method that find compras by id
	 * @autor CACP - 5/02/2021
	 * @param idcompra
	 * @return
	 */
	public Compras getComprasById(Integer idCompra) {
		return comprasRepository.findById(idCompra).orElse(null);
	}
}
