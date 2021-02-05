/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idclientecompra")
	private Clientes cliente;	

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idproductocompra")
	private Productos producto;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idvendedorcompra")
	private Vendedores vendedor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar fechacompra;
	
	@OneToMany(mappedBy = "compra")
	private Set<Estadocompras> estadocompras = new HashSet<>();
}
