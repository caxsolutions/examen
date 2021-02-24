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
@Table(name = "purchasestatus")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "approval status id ",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idpurchasestatus;
	
	@Schema(
            description = "id of the purchase to approve",
            example = "2",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idpurchase")
	private Purchases purchase;
	
	@Schema(
            description = "approval status code, 1: to be approved, 2: approved",
            example = "2",
            required = true
    )	
	@Column(nullable = false)
	private Integer state;	
	
	@Schema(
            description = "date of purchase approval ",
            example = "2021-02-01",
            required = true
    )	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date statedate;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseStatus other = (PurchaseStatus) obj;
		if (idpurchasestatus == null) {
			if (other.idpurchasestatus != null)
				return false;
		} else if (!idpurchasestatus.equals(other.idpurchasestatus))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idpurchasestatus == null) ? 0 : idpurchasestatus.hashCode());
		return result;
	}
	
	
	
}
