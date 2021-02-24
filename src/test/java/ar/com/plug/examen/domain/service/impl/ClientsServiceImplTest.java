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

import ar.com.plug.examen.app.api.ClientsApi;
import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.app.mapper.ClientsMapper;
import ar.com.plug.examen.domain.model.Clients;
import ar.com.plug.examen.domain.service.ClientsRepository;

/**
 * @autor luxos CACP - RFAST9 11/02/2021
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientsServiceImplTest {
	
	@InjectMocks
	private ClientsServiceImpl target;

    @Mock
    private ClientsRepository clienteRepository;
    
    protected static final EasyRandom RANDOM = new EasyRandom();
    
 
    private ClientsMapper mapper = new ClientsMapper();

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mapper = new ClientsMapper();
    }
    
    private List<ClientsApi> getExpectedEntityMock() {
        
    	final List<ClientsApi> output = Lists.newArrayList();
        
        output.add(new ClientsApi(1, "1130", "carlos"));
        output.add(new ClientsApi(2, "1140", "juan"));
        output.add(new ClientsApi(3, "1150", "pedro"));
        output.add(new ClientsApi(4, "1160", "franco"));
        output.add(new ClientsApi(5, "1170", "luis"));
        
        return output;
    }

    @Test
    public void saveClients() throws Exception {
    	
    	final ClientsApi api = RANDOM.nextObject(ClientsApi.class);
    	Clients clientes = mapper.map(api, Clients.class);
    	
    	Mockito.when(clienteRepository.save(clientes)).thenReturn(clientes);
    	
    	assertEquals(clientes, mapper.map(target.saveClients(api), Clients.class));
    }

    @Test
    public void getClients() {
    	
    	List<Clients> output = Lists.newArrayList();
    	output.add(new Clients(1, "1130", "carlos"));
        output.add(new Clients(2, "1140", "juan"));
        output.add(new Clients(3, "1150", "pedro"));
        output.add(new Clients(4, "1160", "franco"));
        output.add(new Clients(5, "1170", "luis"));
    	
    	Mockito.when(clienteRepository.findAll()).thenReturn(output);
    	
    	List<ClientsApi> clientesApi = mapper.mapAsList(clienteRepository.findAll(), ClientsApi.class);
    	
    	Mockito.when(clientesApi).thenReturn(getExpectedEntityMock());
    	
    	assertEquals(clientesApi.size(), target.getClients().size());
    	
    }

    @Test
    public void getClientsById() {
    	
    	
    	final ClientsApi api = getExpectedEntityMock().get(0);
    	Clients clientes = mapper.map(api, Clients.class);

    	Optional<Clients> optional = Optional.ofNullable(clientes);
    	
    	Mockito.when(clienteRepository.findById(api.getIdclient())).thenReturn(optional);
    	
    	assertEquals(optional, Optional.ofNullable(mapper.map(target.getClientsById(api.getIdclient()), Clients.class)));
    }
    
    @Test
    public void updateClients() throws Exception {
    	
    	final ClientsApi api = getExpectedEntityMock().get(0);
    	
    	Clients clientes = mapper.map(api, Clients.class);
    	
    	Clients clientesRespuesta = mapper.map(api, Clients.class);
    	
    	Optional<Clients> optionalRespuesta = Optional.ofNullable(clientesRespuesta);
    	
    	String document = "document";
    	String name = "name";
    	
    	clientes.setDocument(document);
    	clientes.setName(name);
    	
    	api.setDocument(document);
    	api.setName(name);
    	
    	Mockito.when(clienteRepository.findById(api.getIdclient())).thenReturn(optionalRespuesta);
    	
    	Mockito.when(clienteRepository.save(clientes)).thenReturn(clientes);
    	
    	ClientsApi result = target.updateClients(api);
    	
    	assertEquals(result.getDocument(), api.getDocument());
    	assertEquals(result.getName() , api.getName());
    }
    
    @Test (expected = ExamenException.class)
    public void deleteclientByIdNull() throws Exception {
    	target.deleteclientById(null);
    }
    
    @Test
    public void deleteclientByIdNotFound() throws Exception {
    	Integer id = 1;
    	
    	final ClientsApi api = getExpectedEntityMock().get(0);
    	
    	Clients clientesRespuesta = mapper.map(api, Clients.class);
    	
    	clientesRespuesta.setIdclient(null);
    	
    	Optional<Clients> optionalRespuesta = Optional.ofNullable(clientesRespuesta);
    	
    	Mockito.when(clienteRepository.findById(id)).thenReturn(optionalRespuesta);
    	MessageApi messageApi = target.deleteclientById(id);
    	assertTrue(messageApi.getMessage().contains("Not found in the system"));
    }
    
    @Test
    public void deleteclientById() throws Exception {
    	Integer id = 1;
    	
    	final ClientsApi api = getExpectedEntityMock().get(0);
    	
    	Clients clientesRespuesta = mapper.map(api, Clients.class);
    	
    	Optional<Clients> optionalRespuesta = Optional.ofNullable(clientesRespuesta);
    	
    	Mockito.when(clienteRepository.findById(id)).thenReturn(optionalRespuesta);
    	
    	Mockito.doNothing().when(clienteRepository).deleteById(id);
    	
    	MessageApi messageApi = target.deleteclientById(id);
    	
    	assertTrue(messageApi.getMessage().contains("customer removed"));
    }
    
    
}
