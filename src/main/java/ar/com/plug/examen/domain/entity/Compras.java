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
@Table(name = "compras")
@NoArgsConstructor
public class Compras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idcompra;
	
	//@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="idclientecompra")
	private Clientes cliente;

	@ManyToOne
	@JoinColumn(name="idproductocompra")
	private Productos producto;

	@ManyToOne
	@JoinColumn(name="idvendedorcompra")
	private Vendedores vendedor;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechacompra;
	
	//@OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	//private Set<Estadocompras> estadocompras = new HashSet<>();
	
	
}
