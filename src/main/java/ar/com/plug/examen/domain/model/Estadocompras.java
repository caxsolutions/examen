/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "estadocompras")
@NoArgsConstructor
@AllArgsConstructor
public class Estadocompras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "id del estado de aprobacion",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idestadocompra;
	
	@Schema(
            description = "id de la compra a aprobar",
            example = "2",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idcompra")
	private Compras compra;
	
	@Schema(
            description = "codigo del estado de aprobacion, 1:por aprobar, 2:aprobado",
            example = "2",
            required = true
    )	
	@Column(nullable = false)
	private Integer estado;	
	
	@Schema(
            description = "fecha de la aprobacion de la compra",
            example = "2021-02-01",
            required = true
    )	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaestado;
	
}
