/**
 * @autor CACP - RFAST9 11/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
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

import ar.com.plug.examen.app.api.ClientsApi;
import ar.com.plug.examen.app.api.PurchasesApi;
import ar.com.plug.examen.app.mapper.ProductsMapper;
import ar.com.plug.examen.domain.model.Clients;
import ar.com.plug.examen.domain.model.PurchaseStatus;
import ar.com.plug.examen.domain.model.Purchases;
import ar.com.plug.examen.domain.service.PurchaseStatusRepository;
import ar.com.plug.examen.domain.service.PurchasesRepository;

/**
 * @autor luxos CACP - RFAST9 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PurchasesServiceImplTest {
	
	@InjectMocks
	private PurchasesServiceImpl target;

    @Mock
    private PurchasesRepository purchasesRepository;
    
    @Mock
    private PurchaseStatusRepository purchaseStatusRepository;
    
    protected static final EasyRandom RANDOM = new EasyRandom();
    
 
    private ProductsMapper mapper = new ProductsMapper();

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mapper = new ProductsMapper();
    }
    
    private List<PurchasesApi> getExpectedEntityMock() {
        
    	final List<PurchasesApi> output = Lists.newArrayList();
        
        output.add(new PurchasesApi(1, 7, 1, 1, new Timestamp(100000000)));
        output.add(new PurchasesApi(1, 7, 1, 1, new Timestamp(200000000)));
        output.add(new PurchasesApi(1, 7, 1, 1, new Timestamp(300000000)));
        
        return output;
    }

    @Test
    public void savePurchase() throws Exception {
    	
    	final PurchasesApi api = RANDOM.nextObject(PurchasesApi.class);
    	Purchases entity = mapper.map(api, Purchases.class);
    	
    	Mockito.when(purchasesRepository.save(entity)).thenReturn(entity);
    	
    	final PurchaseStatus state = RANDOM.nextObject(PurchaseStatus.class);
    	state.setPurchase(entity);
    	
    	Mockito.when(purchaseStatusRepository.save(state)).thenReturn(state);
    	
    	assertEquals(entity, mapper.map(target.savePurchase(api), Purchases.class));
    }
    
    @Test
    public void getPurchases() {
    	List<Purchases> output = Lists.newArrayList();
    	output.add(new Purchases(1,null,null,null,new Date()));
    	output.add(new Purchases(2,null,null,null,new Date()));
    	output.add(new Purchases(3,null,null,null,new Date()));
    	Mockito.when(purchasesRepository.findAll()).thenReturn(output);
    	List<PurchasesApi> purchasesApi = target.getPurchases();
    	assertEquals(purchasesApi.size(), output.size());
    }
    
    @Test
    public void getPurchasesById() {
    	final PurchasesApi api = getExpectedEntityMock().get(0);
    	Purchases purchases = mapper.map(api, Purchases.class);
    	Optional<Purchases> optional = Optional.ofNullable(purchases);
    	Mockito.when(purchasesRepository.findById(api.getIdpurchase())).thenReturn(optional);
    	assertEquals(optional, Optional.ofNullable(mapper.map(target.getPurchasesById(api.getIdpurchase()), Purchases.class)));
    }
    

}
