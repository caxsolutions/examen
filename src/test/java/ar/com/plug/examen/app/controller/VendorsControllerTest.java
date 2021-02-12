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

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.api.VendorsApi;
import ar.com.plug.examen.domain.service.impl.VendorsServiceImpl;
/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class VendorsControllerTest extends AbstractControllerTest{

	/**
	 * @throws Exception
	 */
	public VendorsControllerTest() throws Exception {
		super();
	}

	private static final String URLADDCLIENT = "/api/v1/vendors/addseller";
	private static final String URLGETCLIENTS = "/api/v1/vendors/getsellers";
	private static final String URLGETCLIENT = "/api/v1/vendors/getseller";
	private static final String URLPUTCLIENT = "/api/v1/vendors/updateseller";
	private static final String URLDELCLIENT = "/api/v1/vendors/deleteseller";
	
	@InjectMocks
	private VendorsController vendedoresController;
	
	@Mock
	private VendorsServiceImpl service;

	@Override
	protected Object getTarget() {
		return vendedoresController;
	}

	@Test
	public void testAddseller() throws Exception{
		
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.saveVendedores(cliente)).thenReturn(response);
		
        assertEquals(perform(post(URLADDCLIENT), cliente, new TypeReference<VendorsApi>() {}, status().isCreated()), response);
	}

	@Test
	public void testGetsellers() {
		
		final List<VendorsApi> response = Lists.newArrayList(RANDOM.nextObject(VendorsApi.class));
        
		Mockito.when(service.getVendedores()).thenReturn(response);
        
		assertEquals(perform(get(URLGETCLIENTS), null, new TypeReference<List<VendorsApi>>() {}, status().isOk()), response);
	}

	@Test
	public void testGetseller() {
		
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.getVendedoresById(cliente.getIdseller())).thenReturn(response);
		
        assertEquals(perform(get(URLGETCLIENT + "/"+ cliente.getIdseller()), null, new TypeReference<VendorsApi>() {}, status().isOk()), response);
	}

	@Test
	public void testUpdateseller() throws Exception {
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.updateVendedores(cliente)).thenReturn(response);
		
        assertEquals(perform(put(URLPUTCLIENT), cliente, new TypeReference<VendorsApi>() {}, status().isCreated()), response);
	}
}
