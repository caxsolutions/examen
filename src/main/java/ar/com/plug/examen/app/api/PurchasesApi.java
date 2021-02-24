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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@JsonRootName(value = "purchases")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesApi {

	@JsonProperty(value = "idpurchase")
	private Integer idpurchase;

	@NotBlank
    @NotNull(message = "idclient required")
	@JsonProperty(value = "idclient")
	private Integer idclient;

	@NotBlank
    @NotNull(message = "idproduct required")
	@JsonProperty(value = "idproduct")
	private Integer idproduct;

	@NotBlank
    @NotNull(message = "idseller required")
	@JsonProperty(value = "idseller")
	private Integer idseller;

	@NotBlank
    @NotNull(message = "purchasedate required")
	@JsonProperty(value = "purchasedate")
	private Timestamp purchasedate;
}
