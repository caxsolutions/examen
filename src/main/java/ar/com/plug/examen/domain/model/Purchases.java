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
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
public class Purchases implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "Creation of Purchases",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idpurchase;
	
	@Schema(
            description = "Purchase ID",
            example = "1",
            required = true
    )	
	//@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="idclientpurchase")
	private Clients client;

	@Schema(
            description = "product id ",
            example = "3",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idproductpurchase")
	private Products product;

	@Schema(
            description = "id del vendedor",
            example = "1111334444",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idsellerpurchase")
	private Vendors seller;
	
	@Schema(
            description = "date of purchase",
            example = "2021-02-01",
            required = true
    )	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date purchasedate;
	
	//@OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	//private Set<Estadocompras> estadocompras = new HashSet<>();
	
	
}
