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
import ar.com.plug.examen.app.api.VendorsApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ProductsMapper;
import ar.com.plug.examen.domain.model.Vendors;
import ar.com.plug.examen.domain.service.VendorsRepository;

/**
 * @autor luxos CACP - RFAST9 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class VendorsServiceImplTest {
	
	@InjectMocks
	private VendorsServiceImpl target;

    @Mock
    private VendorsRepository vendorsRepository;
    
    protected static final EasyRandom RANDOM = new EasyRandom();
    
 
    private ProductsMapper mapper = new ProductsMapper();

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mapper = new ProductsMapper();
    }
    
    private List<VendorsApi> getExpectedEntityMock() {
        
    	final List<VendorsApi> output = Lists.newArrayList();
        
        output.add(new VendorsApi(1, "1130", "Pepito", true));
        output.add(new VendorsApi(2, "1140", "Perez", true));
        output.add(new VendorsApi(3, "1150", "andres", true));
        
        return output;
    }

    @Test
    public void saveVendors() throws Exception {
    	
    	final VendorsApi api = RANDOM.nextObject(VendorsApi.class);
    	Vendors entity = mapper.map(api, Vendors.class);
    	
    	Mockito.when(vendorsRepository.save(entity)).thenReturn(entity);
    	
    	assertEquals(entity, mapper.map(target.saveVendors(api), Vendors.class));
    }

    @Test
    public void getVendors() {
    	
    	List<Vendors> output = Lists.newArrayList();
        output.add(new Vendors(1, "1130", "Pepito", true));
        output.add(new Vendors(2, "1140", "Perez", true));
        output.add(new Vendors(3, "1150", "andres", true));
    	
    	Mockito.when(vendorsRepository.findAll()).thenReturn(output);
    	
    	List<VendorsApi> productsApi = mapper.mapAsList(vendorsRepository.findAll(), VendorsApi.class);
    	
    	Mockito.when(productsApi).thenReturn(getExpectedEntityMock());
    	
    	assertEquals(productsApi.size(), target.getVendors().size());
    	
    }

    @Test
    public void getVendorsById() {
    	
    	final VendorsApi api = getExpectedEntityMock().get(0);
    	Vendors entity = mapper.map(api, Vendors.class);

    	Optional<Vendors> optional = Optional.ofNullable(entity);
    	
    	Mockito.when(vendorsRepository.findById(api.getIdseller())).thenReturn(optional);
    	
    	assertEquals(optional, Optional.ofNullable(mapper.map(target.getVendorsById(api.getIdseller()), Vendors.class)));
    }
    
    @Test
    public void updateVendors() throws Exception {
    	
    	final VendorsApi api = getExpectedEntityMock().get(0);
    	
    	Vendors entity = mapper.map(api, Vendors.class);
    	
    	Vendors productsRespuesta = mapper.map(api, Vendors.class);
    	
    	Optional<Vendors> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	String document = "document";
    	String name = "name";
    	
    	entity.setName(document);
    	entity.setCode(name);
    	
    	api.setName(document);
    	api.setCode(name);
    	
    	Mockito.when(vendorsRepository.findById(api.getIdseller())).thenReturn(optionalRespuesta);
    	
    	Mockito.when(vendorsRepository.save(entity)).thenReturn(entity);
    	
    	VendorsApi result = target.updateVendors(api);
    	
    	assertEquals(result.getName(), api.getName());
    	assertEquals(result.getCode() , api.getCode());
    }
    
    @Test (expected = ExamenException.class)
    public void deleteVendorsByIdNull() throws Exception {
    	target.deleteVendorsById(null);
    }
    
    @Test
    public void deleteVendorsByIdNotFound() throws Exception {
    	Integer id = 1;
    	
    	final VendorsApi api = getExpectedEntityMock().get(0);
    	
    	Vendors productsRespuesta = mapper.map(api, Vendors.class);
    	
    	productsRespuesta.setIdseller(null);
    	
    	Optional<Vendors> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	Mockito.when(vendorsRepository.findById(id)).thenReturn(optionalRespuesta);
    	MessageApi messageApi = target.deleteVendorsById(id);
    	assertTrue(messageApi.getMessage().contains("Not found in the system"));
    }
    
    @Test
    public void deleteVendorsById() throws Exception {
    	Integer id = 1;
    	
    	final VendorsApi api = getExpectedEntityMock().get(0);
    	
    	Vendors productsRespuesta = mapper.map(api, Vendors.class);
    	
    	Optional<Vendors> optionalRespuesta = Optional.ofNullable(productsRespuesta);
    	
    	Mockito.when(vendorsRepository.findById(id)).thenReturn(optionalRespuesta);
    	
    	Mockito.doNothing().when(vendorsRepository).deleteById(id);
    	
    	MessageApi messageApi = target.deleteVendorsById(id);
    	
    	assertTrue(messageApi.getMessage().contains("Seller successfully"));
    }
    
    
}
