/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.entity.Clientes;
import ar.com.plug.examen.domain.entity.Productos;
import ar.com.plug.examen.domain.service.impl.ClientesServiceImpl;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/clients")
//@CrossOrigin(origins = "*")
public class ClientesController {

	@Autowired
	private ClientesServiceImpl service;
	
	
    @PostMapping(path = "/addclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addclient(@RequestBody Clientes clients)
    {
        return new ResponseEntity<>(service.saveClientes(clients), HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/getclients")
    public ResponseEntity<?> getclients()
    {
        return new ResponseEntity<>(service.getClientes(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/getclient/{idcliente}")
    public ResponseEntity<?> getclient(@PathVariable Integer idcliente)
    {
    	
    	Clientes clientes = service.getClientesById(idcliente);
    	
    	if(clientes != null) {
    		return new ResponseEntity<>(clientes, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("No se encontró ningún cliente con el código "+ idcliente, HttpStatus.BAD_REQUEST);
    	}
    	
    }

    @PutMapping(path = "/updateclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateclient(@RequestBody Clientes clientes) throws Exception{
    	try {
    		
    		Clientes clientes2 = service.updateClientes(clientes);
    		
    		if(clientes2 == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + clientes.getNombre(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(clientes2, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @DeleteMapping(path = "/deleteclient/{idcliente}")
    public ResponseEntity<?> deleteclient(@PathVariable Integer idcliente)throws Exception{
    	try {
    		return new ResponseEntity<>(service.deleteclienteById(idcliente), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    
}
