/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "vendedores")
@NoArgsConstructor
@AllArgsConstructor
public class Vendedores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "id del vendedor",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idvendedor;
	
	@Schema(
            description = "Codigo del vendedor",
            example = "1010",
            required = true
    )	
	@Column(nullable = false, length = 10)
	private String codigo;

	@Schema(
            description = "nombre del vendedor",
            example = "maria perez",
            required = true
    )	
	@Column(nullable = false, length = 200)
	private String nombre;

	@Schema(
            description = "Codigo de habilitacion del vendedor",
            example = "true",
            required = true
    )	
	@Column(nullable = false)
	private Boolean habiltiado;
	
	//@OneToMany(mappedBy = "vendedor", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	//private Set<Compras> compras = new HashSet<>();
	
}
