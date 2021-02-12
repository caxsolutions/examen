/**
 * @autor CACP - 5/02/2021
 */
package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Clients;

/**
 * @autor luxos CACP - 5/02/2021
 *
 */
public interface ClientsRepository extends JpaRepository<Clients, Integer>{

	
	public Clients findByDocument(String document);
}
