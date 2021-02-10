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
import ar.com.plug.examen.domain.model.Vendedores;
import ar.com.plug.examen.domain.service.impl.VendedoresServiceImpl;
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
@RequestMapping(path = "/vendors")
@Slf4j
@Tag(name = "Vendedores", description = "ABM Vendedores")
public class VendedoresController {

	@Autowired
	private VendedoresServiceImpl service;
	
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param sellers
	 * @return
	 */
	@Operation(
			summary = "Crear Vendedores",
			description = "Creacion de Vendedores.",
			tags = "Vendedores"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful - Created",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Vendedores.class)
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
    @PostMapping(path = "/addseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addseller(@RequestBody Vendedores sellers){
		try {
			log.info("[addseller] adicionando vendedor.");
			return new ResponseEntity<>(service.saveVendedores(sellers), HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[addseller] Error adicionando vendedor.",e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
	@Operation(
			summary = "Retorna todos los vendedores",
			description = "consultar todos los vendedores.",
			tags = "Vendedores"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Vendedores.class)
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
    @GetMapping(path = "/getsellers")
    public ResponseEntity<?> getsellers(){
		try {
			log.info("[getsellers] consultando todos los vendedores.");
			return new ResponseEntity<>(service.getVendedores(), HttpStatus.OK);
		} catch (Exception e) {
			log.warn("[getsellers] consultando todos los vendedores.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idsellere
     * @return
     */
	@Operation(
			summary = "Retorna vendedor por id",
			description = "consultar vendedor por Id.",
			tags = "Vendedores"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Vendedores.class)
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
    @GetMapping(path = "/getseller/{idsellere}")
    public ResponseEntity<?> getseller(@PathVariable Integer idsellere){
    	try {
    		log.info("[getseller] consultando vendedor por id.");
    		Vendedores vendedores = service.getVendedoresById(idsellere);
    		
    		if(vendedores != null){
    			return new ResponseEntity<>(vendedores, HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el código " + idsellere, HttpStatus.BAD_REQUEST);
    		}
		} catch (Exception e) {
			log.warn("[getseller] Error consultando vendedor por id.", e);
			return badRequest(e);
		}
    }

    /**
     * endpoint updating the registry 
     * @autor CACP - 8/02/2021
     * @param vendors
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "actualiza informacion del vendedor",
			description = "actualizar vendedor por Id.",
			tags = "Vendedores"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Vendedores.class)
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
    @PutMapping(path = "/updateseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateseller(@RequestBody Vendedores vendors) throws Exception{
    	try {
    		log.info("[updateseller] actualizando vendedor.");
    		Vendedores vendedores = service.updateVendedores(vendors);
    		
    		if(vendedores == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + vendors.getNombre(), HttpStatus.BAD_REQUEST);
    		}
    		
    		return new ResponseEntity<>(service.updateVendedores(vendors), HttpStatus.CREATED);
    		
		} catch (Exception e) {
			log.warn("[updateseller] Error actualizando vendedor.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that deletes a record 
     * @autor CACP - 8/02/2021
     * @param idsellere
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "eliminar vendedor por id",
			description = "elimina vendedor por Id.",
			tags = "Vendedores"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = "Request is sccuessful - Accepted",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Vendedores.class)
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
    @DeleteMapping(path = "/deleteseller/{idsellere}")
    public ResponseEntity<?> deleteseller(@PathVariable Integer idsellere)throws Exception{
    	try {
    		log.info("[deleteseller] eliminando vendedor.");
    		return new ResponseEntity<>(service.deleteVendedoresById(idsellere), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.warn("[deleteseller] Error eliminando vendedor.", e);
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
