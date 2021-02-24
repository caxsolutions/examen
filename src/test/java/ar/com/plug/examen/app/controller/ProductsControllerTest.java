/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.controller;

import static org.junit.Assert.assertEquals;
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

import ar.com.plug.examen.app.api.ProductsApi;
import ar.com.plug.examen.domain.service.impl.ProductsServiceImpl;
/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest extends AbstractControllerTest{

	/**
	 * @throws Exception
	 */
	public ProductsControllerTest() throws Exception {
		super();
	}

	private static final String URLADDCLIENT = "/api/v1/products/addproduct";
	private static final String URLGETCLIENTS = "/api/v1/products/getproducts";
	private static final String URLGETCLIENT = "/api/v1/products/getproduct";
	private static final String URLPUTCLIENT = "/api/v1/products/updateproduct";
	private static final String URLDELCLIENT = "/api/v1/products/deleteproduct";
	
	@InjectMocks
	private ProductsController productosController;
	
	@Mock
	private ProductsServiceImpl service;

	@Override
	protected Object getTarget() {
		return productosController;
	}

	@Test
	public void testAddProduct() throws Exception{
		
		final ProductsApi cliente = RANDOM.nextObject(ProductsApi.class);
		final ProductsApi response = RANDOM.nextObject(ProductsApi.class);
		
		Mockito.when(service.saveProducts(cliente)).thenReturn(response);
		
        assertEquals(perform(post(URLADDCLIENT), cliente, new TypeReference<ProductsApi>() {}, status().isCreated()), response);
	}

	@Test
	public void testGetProducts() {
		
		final List<ProductsApi> response = Lists.newArrayList(RANDOM.nextObject(ProductsApi.class));
        
		Mockito.when(service.getProducts()).thenReturn(response);
        
		assertEquals(perform(get(URLGETCLIENTS), null, new TypeReference<List<ProductsApi>>() {}, status().isOk()), response);
	}

	@Test
	public void testGetProduct() {
		
		final ProductsApi cliente = RANDOM.nextObject(ProductsApi.class);
		final ProductsApi response = RANDOM.nextObject(ProductsApi.class);
		
		Mockito.when(service.getProductById(cliente.getIdproduct())).thenReturn(response);
		
        assertEquals(perform(get(URLGETCLIENT + "/"+ cliente.getIdproduct()), null, new TypeReference<ProductsApi>() {}, status().isOk()), response);
	}

	@Test
	public void testUpdateProduct() throws Exception {
		final ProductsApi cliente = RANDOM.nextObject(ProductsApi.class);
		final ProductsApi response = RANDOM.nextObject(ProductsApi.class);
		
		Mockito.when(service.updateProducts(cliente)).thenReturn(response);
		
        assertEquals(perform(put(URLPUTCLIENT), cliente, new TypeReference<ProductsApi>() {}, status().isCreated()), response);
	}

}
