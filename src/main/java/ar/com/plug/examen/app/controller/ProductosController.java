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

import ar.com.plug.examen.domain.model.ErrorObject;
import ar.com.plug.examen.domain.model.Productos;
import ar.com.plug.examen.domain.service.impl.ProductosServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/products")
@Slf4j
@Tag(name = "Productos", description = "ABM Productos")
public class ProductosController {

	@Autowired
	private ProductosServiceImpl service;
	
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param products
	 * @return
	 */
	@Operation(
			summary = "Crear productos",
			description = "Creacion de productos.",
			tags = "Productos"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful - Created",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Productos.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)
    @PostMapping(path = "/addproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Productos products){
		try {
			log.info("[addProduct] adicionando producto.");
			return new ResponseEntity<>(service.saveProductos(products), HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[addProduct] Error adicionando producto.",e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
	@Operation(
			summary = "Retorna todos los productos",
			description = "consutlar todos los productos.",
			tags = "Productos"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Productos.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @GetMapping(path = "/getproducts")
    public ResponseEntity<?> getProducts(){
		try {
			log.info("[getProducts] consultando todos los productos.");
			return new ResponseEntity<>(service.getProductos(), HttpStatus.OK);
		} catch (Exception e) {
			log.warn("[getProducts] consultando todos los productos.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idproducto
     * @return
     */
	@Operation(
			summary = "Retorna producto por id",
			description = "consutlar producto por Id.",
			tags = "Productos"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Productos.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @GetMapping(path = "/getproduct/{idproducto}")
    public ResponseEntity<?> getProduct(@PathVariable Integer idproducto){
		try {
			log.info("[getProduct] consultando producto por id.");
			Productos productos = service.getProductoById(idproducto);
			
			if(productos != null) {
				return new ResponseEntity<>(productos, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No se encontró ningún producto con el código "+ idproducto, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.warn("[getProduct] Erro consultando producto por id.", e);
			return badRequest(e);
		}
    }

    /**
     * endpoint updating the registry 
     * @autor CACP - 8/02/2021
     * @param products
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "actualiza informacion del producto",
			description = "actualizar producto por Id.",
			tags = "Productos"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Productos.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @PutMapping(path = "/updateproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Productos products) throws Exception{
    	try {
    		log.info("[updateProduct] actualizando producto.");
    		Productos productos = service.updateProductos(products);
    		
    		if(productos == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + products.getDescripcion(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(productos, HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[updateProduct] Error actualizando producto.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that deletes a record 
     * @autor CACP - 8/02/2021
     * @param idproducto
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "eliminar producto por id",
			description = "elimina producto por Id.",
			tags = "Productos"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = "Request is sccuessful - Accepted",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Productos.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @DeleteMapping(path = "/deleteproduct/{idproducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer idproducto)throws Exception{
    	try {
    		log.info("[deleteProduct] eliminando producto.");
    		return new ResponseEntity<>(service.deleteProductoById(idproducto), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.warn("[deleteProduct] Error eliminando producto.", e);
			return badRequest(e);
		}
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.NOT_FOUND.toString(), "Member not found"),
                HttpStatus.NOT_FOUND
        );
    }

    private ResponseEntity<?> badRequest(Throwable throwable) {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.BAD_REQUEST.toString(), "Bad request"),
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<?> conflict() {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.CONFLICT.toString(), "Member already exists"),
                HttpStatus.CONFLICT
        );
    }
}
