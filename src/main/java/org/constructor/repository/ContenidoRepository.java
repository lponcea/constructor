package org.constructor.repository;

import org.constructor.domain.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface ContenidoRepository.
 */
@SuppressWarnings("unused")
@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {

}
