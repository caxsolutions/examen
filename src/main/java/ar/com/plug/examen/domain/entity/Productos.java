/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idproducto;
	
	@Column(nullable = false, length = 10)
	private String codigo;
	@Column(length = 100)
	private String descripcion;
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private Boolean habiltiado;
	
	@OneToMany(mappedBy = "producto")
	private Set<Compras> compras = new HashSet<>();

}
