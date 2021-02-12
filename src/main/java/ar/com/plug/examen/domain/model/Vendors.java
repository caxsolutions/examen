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
@Table(name = "vendors")
@NoArgsConstructor
@AllArgsConstructor
public class Vendors implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "seller id",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idseller;
	
	@Schema(
            description = "Vendor code",
            example = "1010",
            required = true
    )	
	@Column(nullable = false, length = 10)
	private String code;

	@Schema(
            description = "Seller Name ",
            example = "maria perez",
            required = true
    )	
	@Column(nullable = false, length = 200)
	private String name;

	@Schema(
            description = "Vendor qualification code",
            example = "true",
            required = true
    )	
	@Column(nullable = false)
	private Boolean enabled;
	
	//@OneToMany(mappedBy = "vendedor", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	//private Set<Compras> compras = new HashSet<>();
	
}
