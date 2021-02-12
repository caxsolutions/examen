/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Purchases;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
public interface PurchasesRepository extends JpaRepository<Purchases, Integer>{

}
