/**
 * @autor CACP - 11/02/2021
 */
package ar.com.plug.examen.app.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @autor luxos CACP - 11/02/2021
 *
 */
@JsonRootName(value = "clients")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsApi {

	@JsonProperty(value = "idclient")
	private Integer idclient;
	
	@NotBlank
	@NotNull(message = "Document required")
    @JsonProperty(value = "document", required = true)
	private String document;

	@NotBlank
    @NotNull(message = "Name required")
	@JsonProperty(value = "name", required = true)
	private String name;
}
