/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.api;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@JsonRootName(value = "purchasestatus")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_EMPTY)
@Data
public class PurchaseStatusApi {

	@JsonProperty(value = "idpurchasestatus")
	private Integer idpurchasestatus;
	
	@NotBlank
	@NotNull(message = "idpurchase required")
	@JsonProperty(value = "idpurchase")
	private Integer idpurchase;

	@NotBlank
	@NotNull(message = "state required")
	@JsonProperty(value = "state")
	private Integer state;

	@NotBlank
	@NotNull(message = "statedate required")
	@JsonProperty(value = "statedate")
	private Timestamp statedate;
}
