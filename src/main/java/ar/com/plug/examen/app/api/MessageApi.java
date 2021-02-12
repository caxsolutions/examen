/**
 * @autor CACP - RFAST9 12/02/2021
 */
package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @autor luxos CACP - RFAST9 12/02/2021
 *
 */
@JsonRootName(value = "message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageApi {

	@JsonProperty
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}