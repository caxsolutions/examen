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
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(
            description = "Creacion de clientes",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idcliente;
	@Schema(
            description = "Documento del cliente",
            example = "1111334444",
            required = true
    )	
	@Column(nullable = false, length = 15)
	private String documento;
	
	@Schema(
            description = "Nombre del cliente",
            example = "pepito perez",
            required = true
    )	
	@Column(nullable = false, length = 200)
	private String nombre;
	
}
