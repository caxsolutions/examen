/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.entity.Compras;
import ar.com.plug.examen.domain.entity.Estadocompras;
import ar.com.plug.examen.domain.service.impl.ComprasServiceImpl;
import ar.com.plug.examen.domain.service.impl.EstadocomprasServiceImpl;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/shop")
//@CrossOrigin(origins = "*")
public class ComprasController {

	@Autowired
	private ComprasServiceImpl service;
	@Autowired
	private EstadocomprasServiceImpl serviceEstadoCompras;
	
	/**
	 * endpoint create a purchase record  
	 * @autor CACP - 8/02/2021
	 * @param products
	 * @return
	 */
    @PostMapping(path = "/addshop", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Compras products)throws Exception{
    	try {
    		return new ResponseEntity<>(service.saveCompras(products), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    /**
     * endpoint get all record
     * @autor CACP - 8/02/2021
     * @return
     */
    @GetMapping(path = "/getshops")
    public ResponseEntity<?> getshops()
    {
        return new ResponseEntity<>(service.getCompras(), HttpStatus.OK);
    }

    /**
     * endpoint get shop by id
     * @autor CACP - 8/02/2021
     * @return
     */
    @GetMapping(path = "/getshop/{idcompra}")
    public ResponseEntity<?> getshop(@PathVariable Integer idcompra)
    {
    	
    	Compras compras = service.getComprasById(idcompra);
    	
    	if(compras != null) {
    		return new ResponseEntity<>(compras, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("No se encontró ninguna compra con el código "+ idcompra, HttpStatus.BAD_REQUEST);
    	}
    }
    
    /**
     * method that records the approval of a purchase
     * @autor CACP - 8/02/2021
     * @param clientes
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/registerpurchase", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerpurchase(@RequestBody Estadocompras estadocompras) throws Exception{
    	try {
    		
    		Estadocompras estadocomprasSave = serviceEstadoCompras.saveEstadocompras(estadocompras);
    		
    		if(estadocomprasSave == null) {
    			return new ResponseEntity<>("No se pudo registrar la aprobacion de la compra " + estadocompras.getCompra().getProducto(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(estadocomprasSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    
}
