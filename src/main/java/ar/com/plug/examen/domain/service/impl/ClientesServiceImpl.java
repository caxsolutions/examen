/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.domain.model.Clientes;
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
	@Transactional
	public Clientes saveClientes(Clientes Clientes) {
		return clienteRepository.save(Clientes);
	}

	/**
	 * method that find clientes
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<Clientes> getClientes() {
		return clienteRepository.findAll();
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
	
	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param clientes
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public Clientes updateClientes(Clientes clientes) throws Exception {
		
		if(clientes == null || clientes.getIdcliente() == null) {
			throw new Exception("El campo idcliente no puede ser nulo.");
		}
		
		Clientes cliente = getClientesById(clientes.getIdcliente());
		
		if(cliente != null && cliente.getIdcliente() != null) {
			
			cliente.setDocumento(clientes.getDocumento());
			cliente.setNombre(clientes.getNombre());
			
			return clienteRepository.save(cliente);
		}
		
		return null;
	}
	
	/**
	 * method that delete entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	@Transactional
	public String deleteclienteById(Integer idcliente) throws Exception{
		
		if(idcliente == null) {
			throw new Exception("El campo idcliente no puede ser nulo.");
		}
		
		Clientes cliente = getClientesById(idcliente);
		
		if(cliente != null && cliente.getIdcliente() != null) {
			
			clienteRepository.deleteById(idcliente);
			
			return "cliente eliminado con exito.";
			
		}else {
			return "cliente con el id " + idcliente + " No se encontro en el sistema.";
		}
	}	
}
