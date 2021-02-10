/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Clientes;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

	
	public Clientes findByDocumento(String documento);
}
