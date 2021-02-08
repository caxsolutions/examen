/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.entity;

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
public class Estadocompras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idestadocompra;
	
	@ManyToOne
	@JoinColumn(name="idcompra")
	private Compras compra;
	
	@Column(nullable = false)
	private Integer estado;	
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaestado;
	
}
