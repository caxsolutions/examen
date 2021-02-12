/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientsApi;
import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ClientsMapper;
import ar.com.plug.examen.domain.model.Clients;
import ar.com.plug.examen.domain.service.ClientsRepository;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@Service
public class ClientsServiceImpl {
	
	@Autowired
	private ClientsRepository clienteRepository;
	
	/**
	 * method that creates an entity 
	 * @autor CACP - 5/02/2021
	 * @param Clients
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ClientsApi saveClientes(ClientsApi clientesApi) throws Exception {
		
		ClientsMapper mapper = new ClientsMapper();
		
		Clients clientes = mapper.map(clientesApi, Clients.class);
		
		Clients clientes2 = clienteRepository.save(clientes);
		return mapper.map(clientes2, ClientsApi.class);
	}

	/**
	 * method that find clientes
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	public List<ClientsApi> getClientes() {
		
		ClientsMapper mapper = new ClientsMapper();
		
		List<Clients> clienteList =  clienteRepository.findAll();
		
		return mapper.mapAsList(clienteList, ClientsApi.class);
	}

	/**
	 * method that find clientes by id
	 * @autor CACP - 5/02/2021
	 * @param idCliente
	 * @return
	 */
	public ClientsApi getClientesById(Integer idCliente) {
		ClientsMapper mapper = new ClientsMapper();
		Clients clientes = clienteRepository.findById(idCliente).orElse(null);
		return mapper.map(clientes, ClientsApi.class);
	}
	
	/**
	 * method that update an entity 
	 * @autor CACP - 5/02/2021
	 * @param clientes
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ClientsApi updateClientes(ClientsApi clientes) throws Exception {
		
		if(clientes == null || clientes.getIdclient() == null) {
			throw new ExamenException("The customerid field cannot be null.");
		}
		
		ClientsMapper mapper = new ClientsMapper();
		
		Clients clienteEn = clienteRepository.findById(clientes.getIdclient()).orElse(null);
		
		if(clienteEn != null && clienteEn.getIdclient() != null) {
			
			clienteEn.setDocument(clientes.getDocument());
			clienteEn.setName(clientes.getName());
		}
		
		return mapper.map(clienteRepository.save(clienteEn), ClientsApi.class);
	}
	
	/**
	 * method that delete entity by id 
	 * @autor CACP - 5/02/2021
	 * @return
	 */
	@Transactional
	public MessageApi deleteclienteById(Integer idcliente) throws Exception{
		
		if(idcliente == null) {
			throw new ExamenException("The customerid field cannot be null.");
		}
		
		MessageApi api = new MessageApi();
		
		Clients clienteEn = clienteRepository.findById(idcliente).orElse(null);
		
		if(clienteEn != null && clienteEn.getIdclient() != null) {
			
			clienteRepository.deleteById(clienteEn.getIdclient());
			
			api.setMessage("customer removed successfully.");
			
		}else {
			api.setMessage("client with id " + idcliente + " Not found in the system.");
		}
		return api;
	}
	
}
