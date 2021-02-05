/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.entity.Clientes;
import ar.com.plug.examen.domain.service.ClientesRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class ClientesServiceImpl {
	
	@Autowired
	private ClientesRepository clienteRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Clientes
	 * @return
	 */
	public Clientes saveClientes(Clientes Clientes) {
		return clienteRepository.save(Clientes);
	}
	
	/**
	 * method that find clientes by id
	 * @autor CACP - 5/02/2021
	 * @param idCliente
	 * @return
	 */
	public Clientes getClientesById(Integer idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}
	
	/**
	 * method that find clientes by documento
	 * @autor CACP - 5/02/2021
	 * @param documento
	 * @return
	 */
	public Clientes getClientesByDocument(String documento) {
		return clienteRepository.findByDocumento(documento);
	}
}
