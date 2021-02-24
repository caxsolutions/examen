/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.api;

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
@JsonRootName(value = "vendors")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorsApi {

	@JsonProperty(value = "idseller")
	private Integer idseller;
	
	@NotBlank
	@NotNull(message = "code required")
	@JsonProperty(value = "code")
	private String code;

	@NotBlank
	@NotNull(message = "name required")
	@JsonProperty(value = "name")
	private String name;

	@NotBlank
	@NotNull(message = "enabled required")
	@JsonProperty(value = "enabled")
	private Boolean enabled;
}
