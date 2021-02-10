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
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "Id del producto",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idproducto;
	
	@Schema(
            description = "Codigo del producto",
            example = "001",
            required = true
    )	
	@Column(nullable = false, length = 10)
	private String codigo;

	@Schema(
            description = "descripcion del producto",
            example = "Televisor SONY 30 pulgadas",
            required = true
    )	
	@Column(length = 100)
	private String descripcion;

	@Schema(
            description = "valor del producto",
            example = "1200000",
            required = true
    )	
	@Column(nullable = false)
	private Double valor;
	
	@Schema(
            description = "indicador de estado habilitado",
            example = "true",
            required = true
    )	
	@Column(nullable = false)
	private Boolean habiltiado;
	
	//@OneToMany(mappedBy = "producto", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	//private Set<Compras> compras = new HashSet<>();

}
