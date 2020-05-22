package org.constructor.repository;

import org.constructor.domain.Ficha;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ficha entity.
 */
@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    /*@Query(value = "select distinct ficha from Ficha ficha left join fetch ficha.colaboradors",
        countQuery = "select count(distinct ficha) from Ficha ficha")
    Page<Ficha> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ficha from Ficha ficha left join fetch ficha.colaboradors")
    List<Ficha> findAllWithEagerRelationships();

    @Query("select ficha from Ficha ficha left join fetch ficha.colaboradors where ficha.id =:id")
    Optional<Ficha> findOneWithEagerRelationships(@Param("id") Long id);*/
    
}
