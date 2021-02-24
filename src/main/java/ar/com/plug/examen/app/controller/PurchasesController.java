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

import ar.com.plug.examen.app.api.PurchaseStatusApi;
import ar.com.plug.examen.app.api.PurchasesApi;
import ar.com.plug.examen.app.config.ConstantsMessage;
import ar.com.plug.examen.app.exception.ExamenException;
import ar.com.plug.examen.domain.model.ErrorObject;
import ar.com.plug.examen.domain.service.impl.PurchaseEstatusServiceImpl;
import ar.com.plug.examen.domain.service.impl.PurchasesServiceImpl;
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
@RequestMapping(path = "/api/v1/shop")
@Slf4j
@Tag(name = "Purchases", description = "ABM Purchases")
public class PurchasesController {

	@Autowired
	private PurchasesServiceImpl service;
	@Autowired
	private PurchaseEstatusServiceImpl servicePurchaseStatus;
	
	/**
	 * endpoint creating a record 
	 * @autor CACP - 8/02/2021
	 * @param product
	 * @return
	 */
	@Operation(
			summary = "register a purchase ",
			description = "Shopping record.",
			tags = "Purchases"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_CREATE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = PurchasesApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)
    @PostMapping(path = "/addshop", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addShop(@RequestBody PurchasesApi products)throws Exception{
		log.info("[addShop] adicionando compra.");
		return new ResponseEntity<>(service.savePurchase(products), HttpStatus.CREATED);
    }

    /**
     * endpoint that returns all records 
     * @autor CACP - 8/02/2021
     * @return
     */
	@Operation(
			summary = "Returns all purchase records ",
			description = "view all purchase records .",
			tags = "Purchases"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = PurchasesApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)
    @GetMapping(path = "/getshops")
    public ResponseEntity<?> getshops(){
		log.info("[getshops] consultando todas las compras.");
		return new ResponseEntity<>(service.getPurchases(), HttpStatus.OK);
    }

    /**
     * endpoint that returns a record by id 
     * @autor CACP - 8/02/2021
     * @param idcompra
     * @return
     */
	@Operation(
			summary = "Returns purchase by id ",
			description = "consult purchase by ID .",
			tags = "Purchases"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = PurchasesApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)
    @GetMapping(path = "/getshop/{idcompra}")
    public ResponseEntity<?> getshop(@PathVariable Integer idcompra){
		log.info("[getshop] consultando compra por id.");
		PurchasesApi compras = service.getPurchasesById(idcompra);
		
		if(compras != null) {
			return new ResponseEntity<>(compras, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No purchase found with the code "+ idcompra, HttpStatus.BAD_REQUEST);
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
			summary = "Approval of a purchase ",
			description = "Record of approval of a purchase. ",
			tags = "Purchases"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_CREATE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = PurchaseStatusApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
	@PostMapping(path = "/registerpurchase", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerpurchase(@RequestBody PurchaseStatusApi estadocompras) throws Exception{
		log.info("[registerpurchase] registrando compra.");
		PurchaseStatusApi estadocomprasSave = servicePurchaseStatus.saveEstadocompras(estadocompras);
		
		if(estadocomprasSave == null) {
			throw new ExamenException("The purchase approval could not be registered  " + estadocompras.getIdpurchase());
		}

		return new ResponseEntity<>(estadocomprasSave, HttpStatus.CREATED);
    }
}
