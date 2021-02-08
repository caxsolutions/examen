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

import ar.com.plug.examen.domain.entity.Vendedores;
import ar.com.plug.examen.domain.service.impl.VendedoresServiceImpl;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/vendors")
//@CrossOrigin(origins = "*")
public class VendedoresController {

	@Autowired
	private VendedoresServiceImpl service;
	
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param sellers
	 * @return
	 */
    @PostMapping(path = "/addseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addseller(@RequestBody Vendedores sellers)
    {
        return new ResponseEntity<>(service.saveVendedores(sellers), HttpStatus.CREATED);
    }
    
    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
    @GetMapping(path = "/getsellers")
    public ResponseEntity<?> getsellers()
    {
        return new ResponseEntity<>(service.getVendedores(), HttpStatus.OK);
    }
    
    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idsellere
     * @return
     */
    @GetMapping(path = "/getseller/{idsellere}")
    public ResponseEntity<?> getseller(@PathVariable Integer idsellere){
    	
    	Vendedores vendedores = service.getVendedoresById(idsellere);
    	
    	if(vendedores != null){
    		return new ResponseEntity<>(vendedores, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("No se encontró ningún vendedor con el código " + idsellere, HttpStatus.BAD_REQUEST);
    	}
    }

    /**
     * endpoint updating the registry 
     * @autor CACP - 8/02/2021
     * @param vendors
     * @return
     * @throws Exception
     */
    @PutMapping(path = "/updateseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateseller(@RequestBody Vendedores vendors) throws Exception{
    	try {
    		
    		Vendedores vendedores = service.updateVendedores(vendors);
    		
    		if(vendedores == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + vendors.getNombre(), HttpStatus.BAD_REQUEST);
    		}
    		
    		return new ResponseEntity<>(service.updateVendedores(vendors), HttpStatus.CREATED);
    		
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    /**
     * endpoint that deletes a record 
     * @autor CACP - 8/02/2021
     * @param idsellere
     * @return
     * @throws Exception
     */
    @DeleteMapping(path = "/deleteseller/{idsellere}")
    public ResponseEntity<?> deleteseller(@PathVariable Integer idsellere)throws Exception{
    	try {
    		return new ResponseEntity<>(service.deleteVendedoresById(idsellere), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    
}
