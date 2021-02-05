/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.plug.examen.domain.entity.Productos;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */

@JsonRootName(value = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsApi extends Productos{

}
