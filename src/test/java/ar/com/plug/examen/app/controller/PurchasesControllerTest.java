/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import ar.com.plug.examen.app.api.PurchasesApi;
import ar.com.plug.examen.domain.service.impl.PurchaseEstatusServiceImpl;
import ar.com.plug.examen.domain.service.impl.PurchasesServiceImpl;
/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PurchasesControllerTest extends AbstractControllerTest{

	/**
	 * @throws Exception
	 */
	public PurchasesControllerTest() throws Exception {
		super();
	}

	private static final String URLADDCLIENT = "/api/v1/shop/addshop";
	private static final String URLGETCLIENTS = "/api/v1/shop/getshops";
	private static final String URLGETCLIENT = "/api/v1/shop/getshop";
	private static final String URLPUTCLIENT = "/api/v1/shop/registerpurchase";
	
	@InjectMocks
	private PurchasesController comprasController;
	
	@Mock
	private PurchasesServiceImpl service;

	@Mock
	private PurchaseEstatusServiceImpl serviceEstado;

	@Override
	protected Object getTarget() {
		return comprasController;
	}

	@Test
	public void testAddShop() throws Exception{
		
		final PurchasesApi cliente = RANDOM.nextObject(PurchasesApi.class);
		final PurchasesApi response = RANDOM.nextObject(PurchasesApi.class);
		
		Mockito.when(service.savePurchase(cliente)).thenReturn(response);
		
        assertEquals(perform(post(URLADDCLIENT), cliente, new TypeReference<PurchasesApi>() {}, status().isCreated()), response);
	}

	@Test
	public void testGetshops() {
		
		final List<PurchasesApi> response = Lists.newArrayList(RANDOM.nextObject(PurchasesApi.class));
        
		Mockito.when(service.getPurchases()).thenReturn(response);
        
		assertEquals(perform(get(URLGETCLIENTS), null, new TypeReference<List<PurchasesApi>>() {}, status().isOk()), response);
	}

	@Test
	public void testGetshop() {
		
		final PurchasesApi cliente = RANDOM.nextObject(PurchasesApi.class);
		final PurchasesApi response = RANDOM.nextObject(PurchasesApi.class);
		
		Mockito.when(service.getPurchasesById(cliente.getIdpurchase())).thenReturn(response);
		
        assertEquals(perform(get(URLGETCLIENT + "/"+ cliente.getIdpurchase()), null, new TypeReference<PurchasesApi>() {}, status().isOk()), response);
	}

}
