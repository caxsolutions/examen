/**
 * @autor CACP - RFAST9 11/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.api.ProductsApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ProductsMapper;
import ar.com.plug.examen.domain.model.Products;
import ar.com.plug.examen.domain.service.ProductRepository;

/**
 * @autor luxos CACP - RFAST9 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceImplTest {
	
	@InjectMocks
	private ProductsServiceImpl target;

    @Mock
    private ProductRepository productRepository;
    
    protected static final EasyRandom RANDOM = new EasyRandom();
    
 
    private ProductsMapper mapper = new ProductsMapper();

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mapper = new ProductsMapper();
    }
    
    private List<ProductsApi> getExpectedEntityMock() {
        
    	final List<ProductsApi> output = Lists.newArrayList();
        
        output.add(new ProductsApi(1, "1130", "TV 2", 30000.0, true));
        output.add(new ProductsApi(2, "1140", "TV 1", 10000.0, true));
        output.add(new ProductsApi(3, "1150", "TV 3", 20000.0, true));
        output.add(new ProductsApi(4, "1160", "TV 4", 30000.0, true));
        output.add(new ProductsApi(5, "1170", "TV 5", 40000.0, true));
        
        return output;
    }

    @Test
    public void saveProducts() throws Exception {
    	
    	final ProductsApi api = RANDOM.nextObject(ProductsApi.class);
    	Products entity = mapper.map(api, Products.class);
    	
    	Mockito.when(productRepository.save(entity)).thenReturn(entity);
    	
    	assertEquals(entity, mapper.map(target.saveProducts(api), Products.class));
    }

    @Test
    public void getProducts() {
    	
    	List<Products> output = Lists.newArrayList();
        output.add(new Products(1, "1130", "TV 2", 30000.0, true));
        output.add(new Products(2, "1140", "TV 1", 10000.0, true));
        output.add(new Products(3, "1150", "TV 3", 20000.0, true));
        output.add(new Products(4, "1160", "TV 4", 30000.0, true));
        output.add(new Products(5, "1170", "TV 5", 40000.0, true));
    	
    	Mockito.when(productRepository.findAll()).thenReturn(output);
    	
    	List<ProductsApi> productsApi = mapper.mapAsList(productRepository.findAll(), ProductsApi.class);
    	
    	Mockito.when(productsApi).thenReturn(getExpectedEntityMock());
    	
    	assertEquals(productsApi.size(), target.getProducts().size());
    	
    }

    @Test
    public void getProductById() {
    	
    	final ProductsApi api = getExpectedEntityMock().get(0);
    	Products entity = mapper.map(api, Products.class);

    	Optional<Products> optional = Optional.ofNullable(entity);
    	
    	Mockito.when(productRepository.findById(api.getIdproduct())).thenReturn(optional);
    	
    	assertEquals(optional, Optional.ofNullable(mapper.map(target.getProductById(api.getIdproduct()), Products.class)));
    }
    
    @Test
    public void updateClient() throws Exception {
    	
    	final ProductsApi api = getExpectedEntityMock().get(0);
    	
    	Products entity = mapper.map(api, Products.class);
    	
    	Products productsRespuesta = mapper.map(api, Products.class);
    	
    	Optional<Products> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	String document = "document";
    	String name = "name";
    	
    	entity.setDescription(document);
    	entity.setCode(name);
    	
    	api.setDescription(document);
    	api.setCode(name);
    	
    	Mockito.when(productRepository.findById(api.getIdproduct())).thenReturn(optionalRespuesta);
    	
    	Mockito.when(productRepository.save(entity)).thenReturn(entity);
    	
    	ProductsApi result = target.updateProducts(api);
    	
    	assertEquals(result.getDescription(), api.getDescription());
    	assertEquals(result.getCode() , api.getCode());
    }
    
    @Test (expected = ExamenException.class)
    public void deleteProductByIdNull() throws Exception {
    	target.deleteProductById(null);
    }
    
    @Test
    public void deleteProductByIdNotFound() throws Exception {
    	Integer id = 1;
    	
    	final ProductsApi api = getExpectedEntityMock().get(0);
    	
    	Products productsRespuesta = mapper.map(api, Products.class);
    	
    	productsRespuesta.setIdproduct(null);
    	
    	Optional<Products> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	Mockito.when(productRepository.findById(id)).thenReturn(optionalRespuesta);
    	MessageApi messageApi = target.deleteProductById(id);
    	assertTrue(messageApi.getMessage().contains("Not found in the system"));
    }
    
    @Test
    public void deleteProductById() throws Exception {
    	Integer id = 1;
    	
    	final ProductsApi api = getExpectedEntityMock().get(0);
    	
    	Products productsRespuesta = mapper.map(api, Products.class);
    	
    	Optional<Products> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	Mockito.when(productRepository.findById(id)).thenReturn(optionalRespuesta);
    	
    	Mockito.doNothing().when(productRepository).deleteById(id);
    	
    	MessageApi messageApi = target.deleteProductById(id);
    	
    	assertTrue(messageApi.getMessage().contains("product removed"));
    }
    
    
}
