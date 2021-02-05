/**
 * @autor CACP -  5/02/2021
 */
package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.entity.Productos;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
public interface ProductoRepository extends JpaRepository<Productos, Integer>{

}
