/**
 * @autor CACP - RFAST9 11/02/2021
 */
package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import ar.com.plug.examen.app.api.PurchaseStatusApi;
import ar.com.plug.examen.app.api.PurchasesApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ProductsMapper;
import ar.com.plug.examen.domain.model.PurchaseStatus;
import ar.com.plug.examen.domain.model.Purchases;
import ar.com.plug.examen.domain.service.PurchaseStatusRepository;
import ar.com.plug.examen.domain.service.PurchasesRepository;

/**
 * @autor luxos CACP - RFAST9 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PurchaseEstatusServiceImplTest {
	
	@InjectMocks
	private PurchaseEstatusServiceImpl target;

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
    
    @Test (expected = Exception.class)
    public void saveEstadocomprasNoPresent() throws Exception {
    	PurchaseStatusApi purchaseStatusApi = RANDOM.nextObject(PurchaseStatusApi.class);
    	final Purchases purchases = null;
    	Optional<Purchases> optional = Optional.ofNullable(purchases);
    	Mockito.when(purchasesRepository.findById(purchaseStatusApi.getIdpurchase())).thenReturn(optional);
    	target.saveEstadocompras(purchaseStatusApi);
    }

    @Test
    public void saveEstadocompras() throws Exception {
    	PurchaseStatusApi purchaseStatusApi = RANDOM.nextObject(PurchaseStatusApi.class);
    	final Purchases purchases = RANDOM.nextObject(Purchases.class);
    	Optional<Purchases> optional = Optional.ofNullable(purchases);
    	Mockito.when(purchasesRepository.findById(purchaseStatusApi.getIdpurchase())).thenReturn(optional);
    	
    	PurchaseStatus estadocompras = mapper.map(purchaseStatusApi, PurchaseStatus.class);
    	
    	Mockito.when(purchaseStatusRepository.save(estadocompras)).thenReturn(estadocompras);
    	
    	PurchaseStatusApi result = target.saveEstadocompras(purchaseStatusApi);
    	
    	assertNotNull(result.getIdpurchasestatus());
    	
    }


}
