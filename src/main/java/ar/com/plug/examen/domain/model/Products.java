/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @autor luxos CACP - 4/02/2021
 *
 */
@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "Product id",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idproduct;
	
	@Schema(
            description = "Product code",
            example = "001",
            required = true
    )	
	@Column(nullable = false, length = 10)
	private String code;

	@Schema(
            description = "Product description",
            example = "Televisor SONY 30 pulgadas",
            required = true
    )	
	@Column(length = 100)
	private String description;

	@Schema(
            description = "product value",
            example = "1200000",
            required = true
    )	
	@Column(nullable = false)
	private Double price;
	
	@Schema(
            description = "status indicator enabled",
            example = "true",
            required = true
    )	
	@Column(nullable = false)
	private Boolean enabled;
	
	//@OneToMany(mappedBy = "producto", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	//private Set<Compras> compras = new HashSet<>();

}
