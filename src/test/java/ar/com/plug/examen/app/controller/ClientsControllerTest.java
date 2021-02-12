/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.collections.Lists;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.com.plug.examen.app.api.ClientsApi;
import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.domain.service.impl.ClientsServiceImpl;
/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientsControllerTest extends AbstractControllerTest{

	/**
	 * @throws Exception
	 */
	public ClientsControllerTest() throws Exception {
		super();
	}

	private static final String URLADDCLIENT = "/api/v1/clients/addclient";
	private static final String URLGETCLIENTS = "/api/v1/clients/getclients";
	private static final String URLGETCLIENT = "/api/v1/clients/getclient";
	private static final String URLPUTCLIENT = "/api/v1/clients/updateclient";
	private static final String URLDELCLIENT = "/api/v1/clients/deleteclient";
	
	@InjectMocks
	private ClientsController clientesController;
	
	@Mock
	private ClientsServiceImpl service;

	@Override
	protected Object getTarget() {
		return clientesController;
	}

	@Test
	public void testAddclient() throws Exception{
		
		final ClientsApi cliente = RANDOM.nextObject(ClientsApi.class);
		final ClientsApi response = RANDOM.nextObject(ClientsApi.class);
		
		Mockito.when(service.saveClientes(cliente)).thenReturn(response);
		
        assertEquals(perform(post(URLADDCLIENT), cliente, new TypeReference<ClientsApi>() {}, status().isCreated()), response);
	}

	@Test
	public void testGetclients() {
		
		final List<ClientsApi> response = Lists.newArrayList(RANDOM.nextObject(ClientsApi.class));
        
		Mockito.when(service.getClientes()).thenReturn(response);
        
		assertEquals(perform(get(URLGETCLIENTS), null, new TypeReference<List<ClientsApi>>() {}, status().isOk()), response);
	}

	@Test
	public void testGetclient() {
		
		final ClientsApi cliente = RANDOM.nextObject(ClientsApi.class);
		final ClientsApi response = RANDOM.nextObject(ClientsApi.class);
		
		Mockito.when(service.getClientesById(cliente.getIdclient())).thenReturn(response);
		
        assertEquals(perform(get(URLGETCLIENT + "/"+ cliente.getIdclient()), null, new TypeReference<ClientsApi>() {}, status().isOk()), response);
	}

	@Test
	public void testUpdateclient() throws Exception {
		final ClientsApi cliente = RANDOM.nextObject(ClientsApi.class);
		final ClientsApi response = RANDOM.nextObject(ClientsApi.class);
		
		Mockito.when(service.updateClientes(cliente)).thenReturn(response);
		
        assertEquals(perform(put(URLPUTCLIENT), cliente, new TypeReference<ClientsApi>() {}, status().isCreated()), response);
	}
}
