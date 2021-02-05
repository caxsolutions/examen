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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.entity.Productos;
import ar.com.plug.examen.domain.service.impl.ProductosServiceImpl;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/products")
//@CrossOrigin(origins = "*")
public class ProductosController {

	@Autowired
	private ProductosServiceImpl service;
	
	
    @PostMapping(path = "/addproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Productos products)
    {
        return new ResponseEntity<>(service.saveProductos(products), HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/getproducts")
    public ResponseEntity<?> getProducts()
    {
        return new ResponseEntity<>(service.getProductos(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/getproduct/{idproducto}")
    public ResponseEntity<?> getProducts(@PathVariable Integer idproducto)
    {
        return new ResponseEntity<>(service.getProductoById(idproducto), HttpStatus.OK);
    }

    @PutMapping(path = "/updateproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Productos products) throws Exception{
    	try {
    		return new ResponseEntity<>(service.updateProductos(products), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    
}
