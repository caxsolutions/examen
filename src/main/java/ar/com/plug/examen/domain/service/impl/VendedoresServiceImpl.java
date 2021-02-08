/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Vendedores saveVendedores(Vendedores Vendedores) {
		return vendedoresRepository.save(Vendedores);
	}

	/**
	 * method that find all entity 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<Vendedores> getVendedores() {
		return vendedoresRepository.findAll();
	}

	/**
	 * method that find entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public Vendedores getVendedoresById(Integer idVendedores) {
		
		return vendedoresRepository.findById(idVendedores).orElse(null);
	}

	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param vendedores
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public Vendedores updateVendedores(Vendedores vendedores) throws Exception {
		
		if(vendedores == null || vendedores.getIdvendedor() == null) {
			throw new Exception("El campo idvendedor no puede ser nulo.");
		}
		
		Vendedores vendedor = getVendedoresById(vendedores.getIdvendedor());
		
		if(vendedor != null && vendedor.getIdvendedor() != null) {
			
			vendedor.setCodigo(vendedores.getCodigo());
			vendedor.setNombre(vendedores.getNombre());
			vendedor.setHabiltiado(vendedores.getHabiltiado());
			
			return vendedoresRepository.save(vendedor);
		}
		
		return null;
	}
	
	/**
	 * method that delete entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	@Transactional
	public String deleteVendedoresById(Integer idVendedores) throws Exception{
		
		if(idVendedores == null) {
			throw new Exception("El campo idvendedor no puede ser nulo.");
		}
		
		Vendedores vendedor = getVendedoresById(idVendedores);
		
		if(vendedor != null && vendedor.getIdvendedor() != null) {
			
			vendedoresRepository.deleteById(idVendedores);
			
			return "Vendedor eliminado con exito.";
			
		}else {
			return "Vendedor con el id " + idVendedores + " No se encontro en el sistema.";
		}
	}
	
}
