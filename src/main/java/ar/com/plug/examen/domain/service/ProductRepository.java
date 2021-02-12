/**
 * @autor CACP -  5/02/2021
 */
package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Products;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
public interface ProductRepository extends JpaRepository<Products, Integer>{

}
