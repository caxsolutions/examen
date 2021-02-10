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

import ar.com.plug.examen.domain.model.Clientes;
import ar.com.plug.examen.domain.model.ErrorObject;
import ar.com.plug.examen.domain.service.impl.ClientesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
@RestController
@RequestMapping(path = "/clients")
@AllArgsConstructor
@Slf4j
@Tag(name = "Clientes", description = "ABM Clientes")
public class ClientesController {

	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param clients
	 * @return
	 */
	@Autowired
	private ClientesServiceImpl service;
	
	@Operation(
			summary = "Crear clientes",
			description = "Creacion de clientes.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = "Request is sccuessful - Created",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Clientes.class)
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
    @PostMapping(path = "/addclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addclient(@RequestBody Clientes clients){
		try {
			log.info("[addclient] adicionando cliente.");
			return new ResponseEntity<>(service.saveClientes(clients), HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[addclient] Error adicionando cliente.",e);
			return badRequest(e);
		}
    }

    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
	@Operation(
			summary = "Retorna todos los clientes",
			description = "consutlar todos los clientes.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Clientes.class)
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
    @GetMapping(path = "/getclients")
    public ResponseEntity<?> getclients(){
    	try {
    		log.info("[getclients] consultando todos los clientes.");
    		return new ResponseEntity<>(service.getClientes(), HttpStatus.OK);
		} catch (Exception e) {
			log.warn("[getclients] Error consultando clientes.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idcliente
     * @return
     */
	@Operation(
			summary = "Retorna cliente por id",
			description = "consutlar cliente por Id.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Clientes.class)
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
    @GetMapping(path = "/getclient/{idcliente}")
    public ResponseEntity<?> getclient(@PathVariable Integer idcliente){
    	try {
    		log.info("[getclient] consultando cliente por id.");
    		Clientes clientes = service.getClientesById(idcliente);
    		if(clientes != null) {
    			return new ResponseEntity<>(clientes, HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>("No se encontró ningún cliente con el código "+ idcliente, HttpStatus.BAD_REQUEST);
    		}
		} catch (Exception e) {
			log.warn("[getclient] Error consultando cliente por id.");
			return badRequest(e);
		}
    }

    /**
     * endpoint updating the registry 
     * @autor CACP - 8/02/2021
     * @param clientes
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "actualiza informacion del cliente",
			description = "actualizar cliente por Id.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Request is sccuessful",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Clientes.class)
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
    @PutMapping(path = "/updateclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateclient(@RequestBody Clientes clientes) throws Exception{
    	try {
    		log.info("[updateclient] actualizando cliente.");
    		Clientes clientes2 = service.updateClientes(clientes);
    		
    		if(clientes2 == null) {
    			return new ResponseEntity<>("No se encontró ningún vendedor con el nombre " + clientes.getNombre(), HttpStatus.BAD_REQUEST);
    		}

    		return new ResponseEntity<>(clientes2, HttpStatus.CREATED);
		} catch (Exception e) {
			log.warn("[updateclient] Error actualizando cliente.", e);
			return badRequest(e);
		}
    }
    
    /**
     * endpoint that deletes a record 
     * @autor CACP - 8/02/2021
     * @param idcliente
     * @return
     * @throws Exception
     */
	@Operation(
			summary = "eliminar cliente por id",
			description = "elimina cliente por Id.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = "Request is sccuessful - accepted",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = Clientes.class)
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
    @DeleteMapping(path = "/deleteclient/{idcliente}")
    public ResponseEntity<?> deleteclient(@PathVariable Integer idcliente)throws Exception{
    	try {
    		log.info("[deleteclient] eliminando cliente.");
    		return new ResponseEntity<>(service.deleteclienteById(idcliente), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.warn("[deleteclient] Error eliminando cliente.",e);
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
