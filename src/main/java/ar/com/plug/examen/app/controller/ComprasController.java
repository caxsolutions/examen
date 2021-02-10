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

import ar.com.plug.examen.domain.model.Compras;
import ar.com.plug.examen.domain.model.ErrorObject;
import ar.com.plug.examen.domain.model.Estadocompras;
import ar.com.plug.examen.domain.service.impl.ComprasServiceImpl;
import ar.com.plug.examen.domain.service.impl.EstadocomprasServiceImpl;
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
@RequestMapping(path = "/shop")
@Slf4j
@Tag(name = "Compras", description = "ABM Compras")
public class ComprasController {

	@Autowired
	private ComprasServiceImpl service;
	@Autowired
	private EstadocomprasServiceImpl serviceEstadoCompras;
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param product
	 * @return
	 */
	@Operation(
			summary = "registrar una compra",
			description = "Registro de compras.",
			tags = "Compras"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful - Created",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Compras.class)
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
    @PostMapping(path = "/addshop", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addShop(@RequestBody Compras products)throws Exception{
    	try {
    		log.info("[addShop] adicionando compra.");
    		return new ResponseEntity<>(service.saveCompras(products), HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[addclient] Error adicionando compra.",e);
			return badRequest(e);
		}
    }

    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
	@Operation(
			summary = "Retorna todos los registros de compras",
			description = "consutlar todos los registros de compras.",
			tags = "Compras"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Compras.class)
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
    @GetMapping(path = "/getshops")
    public ResponseEntity<?> getshops(){
		try {
			log.info("[getshops] consultando todas las compras.");
			return new ResponseEntity<>(service.getCompras(), HttpStatus.OK);
		} catch (Exception e) {
			log.warn("[getshops] Error consultando todas las compras.", e);
			return badRequest(e);
		}
    }

    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idcompra
     * @return
     */
	@Operation(
			summary = "Retorna compra por id",
			description = "consutlar compra por Id.",
			tags = "Compras"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Compras.class)
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
    @GetMapping(path = "/getshop/{idcompra}")
    public ResponseEntity<?> getshop(@PathVariable Integer idcompra){
		try {
			log.info("[getshop] consultando compra por id.");
			Compras compras = service.getComprasById(idcompra);
			
			if(compras != null) {
				return new ResponseEntity<>(compras, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No se encontró ninguna compra con el código "+ idcompra, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.warn("[getshop] Error consultando compra por id.",e);
			return badRequest(e);
		}
    }
    
    /**
     * method that records the approval of a purchase
     * @autor CACP - 8/02/2021
     * @param clientes
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "Aprobacion de una compra",
			description = "Registro de aprobacion de una compra.",
			tags = "Compras"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful - created",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Compras.class)
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
    @PostMapping(path = "/registerpurchase", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerpurchase(@RequestBody Estadocompras estadocompras) throws Exception{
    	try {
    		
    		log.info("[registerpurchase] registrando compra.");
    		Estadocompras estadocomprasSave = serviceEstadoCompras.saveEstadocompras(estadocompras);
    		
    		if(estadocomprasSave == null) {
    			return new ResponseEntity<>("No se pudo registrar la aprobacion de la compra " + estadocompras.getCompra().getProducto(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(estadocomprasSave, HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[registerpurchase] Error registrando compra.", e);
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
