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
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
public class Compras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "Creacion de Compras",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idcompra;
	
	@Schema(
            description = "Id de la compra",
            example = "1",
            required = true
    )	
	//@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="idclientecompra")
	private Clientes cliente;

	@Schema(
            description = "id del producto",
            example = "3",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idproductocompra")
	private Productos producto;

	@Schema(
            description = "id del vendedor",
            example = "1111334444",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idvendedorcompra")
	private Vendedores vendedor;
	
	@Schema(
            description = "fecha de la compra",
            example = "2021-02-01",
            required = true
    )	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechacompra;
	
	//@OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	//private Set<Estadocompras> estadocompras = new HashSet<>();
	
	
}
