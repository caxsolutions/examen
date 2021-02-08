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
	
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param products
	 * @return
	 */
    @PostMapping(path = "/addproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Productos products)
    {
        return new ResponseEntity<>(service.saveProductos(products), HttpStatus.CREATED);
    }
    
    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
    @GetMapping(path = "/getproducts")
    public ResponseEntity<?> getProducts()
    {
        return new ResponseEntity<>(service.getProductos(), HttpStatus.OK);
    }
    
    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idproducto
     * @return
     */
    @GetMapping(path = "/getproduct/{idproducto}")
    public ResponseEntity<?> getProduct(@PathVariable Integer idproducto)
    {
    	Productos productos = service.getProductoById(idproducto);
    	
    	if(productos != null) {
    		return new ResponseEntity<>(productos, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("No se encontró ningún producto con el código "+ idproducto, HttpStatus.BAD_REQUEST);
    	}
    	
    }

    /**
     * endpoint updating the registry 
     * @autor CACP - 8/02/2021
     * @param products
     * @return
     * @throws Exception
     */
    @PutMapping(path = "/updateproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Productos products) throws Exception{
    	try {
    		
    		Productos productos = service.updateProductos(products);
    		
    		if(productos == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + products.getDescripcion(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(productos, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    /**
     * endpoint that deletes a record 
     * @autor CACP - 8/02/2021
     * @param idproducto
     * @return
     * @throws Exception
     */
    @DeleteMapping(path = "/deleteproduct/{idproducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer idproducto)throws Exception{
    	try {
    		return new ResponseEntity<>(service.deleteProductoById(idproducto), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    
}
