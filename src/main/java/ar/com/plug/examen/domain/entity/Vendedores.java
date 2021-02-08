/**
 * @autor CACP - 4/02/2021
 */
package ar.com.plug.examen.domain.entity;

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
public class Vendedores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idvendedor;
	
	@Column(nullable = false, length = 10)
	private String codigo;
	@Column(nullable = false, length = 200)
	private String nombre;
	@Column(nullable = false)
	private Boolean habiltiado;
	
	//@OneToMany(mappedBy = "vendedor", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	//private Set<Compras> compras = new HashSet<>();
	
}
