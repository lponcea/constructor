package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.BloqueComponentes;
import org.constructor.domain.Componente;
import org.constructor.domain.Curso;
import org.constructor.domain.NivelJerarquico;
import org.constructor.domain.NivelesCurso;
import org.constructor.domain.TipoBloqueComponentes;
import org.constructor.domain.TipoComponente;
import org.constructor.repository.BloqueComponentesRepository;
import org.constructor.repository.ComponenteRepository;
import org.constructor.repository.CursoRepository;
import org.constructor.repository.NivelJerarquicoRepository;
import org.constructor.repository.NivelesCursoRepository;
import org.constructor.repository.TipoBloqueComponentesRepository;
import org.constructor.repository.TipoComponenteRepository;
import org.constructor.service.NivelJerarquicoService;
import org.constructor.service.dto.BloqueComponentesDTO;
import org.constructor.service.dto.ComponenteDTO;
import org.constructor.service.dto.NivelJerarquicoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NivelJerarquico}.
 */
@Service
@Transactional
public class NivelJerarquicoServiceImpl  implements NivelJerarquicoService {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(NivelJerarquicoServiceImpl.class);
	
	 /** The nivel jerarquico repository. */
 	private final NivelJerarquicoRepository nivelJerarquicoRepository;
	 
	 /** The bloque componentes repository. */
 	@Autowired
	 private BloqueComponentesRepository bloqueComponentesRepository;
	 
	 /** The componente repository. */
 	@Autowired
	 private ComponenteRepository componenteRepository;
	 
	 /** The tipo bloque componente repository. */
 	@Autowired 
	 private TipoBloqueComponentesRepository tipoBloqueComponenteRepository;
	 
	 /** The tipo componente repository. */
 	@Autowired 
	 private TipoComponenteRepository tipoComponenteRepository;
	 
	 /** The curso repository. */
 	@Autowired
	 private CursoRepository cursoRepository;
	 
	 /** The niveles curso repository. */
 	@Autowired
	 private NivelesCursoRepository nivelesCursoRepository;
	 
	 
	 
	    /**
    	 * Instantiates a new nivel jerarquico service impl.
    	 *
    	 * @param nivelJerarquicoRepository the nivel jerarquico repository
    	 */
    	public NivelJerarquicoServiceImpl(NivelJerarquicoRepository nivelJerarquicoRepository) {
	        this.nivelJerarquicoRepository = nivelJerarquicoRepository;
	    }

	/**
	 * Save.
	 *
	 * @param nivelJerarquicoDTO the nivel jerarquico DTO
	 * @return the nivel jerarquico
	 * @throws Exception the exception
	 */
	@Override
	@Transactional
	public NivelJerarquico save(NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception {
		
		//Guardando NivelJerarquico
		NivelJerarquico nivelJerarquico = new NivelJerarquico();
		nivelJerarquico.setNombre(nivelJerarquicoDTO.getNombre());
		nivelJerarquico.setTipo(nivelJerarquicoDTO.getTipo());
		nivelJerarquico.setInformacionAdicional(nivelJerarquicoDTO.getInformacionAdicional());
		nivelJerarquicoRepository.save(nivelJerarquico);
		
		log.debug("Se guardó correctamente el nivelJerarquico: {}", nivelJerarquico);
		
		//Guardando el BloqueComponente
		for (BloqueComponentesDTO bloqueDTO : nivelJerarquicoDTO.getBloquesComponentes()) {
			BloqueComponentes bloqueComponentes = new BloqueComponentes();
			bloqueComponentes.setOrdenComponente(bloqueDTO.getOrden());
			bloqueComponentes.setTipoBloqueComponentes(bloqueDTO.getTipoBloqueComponente());
				bloqueComponentes.setNivelJerarquico(nivelJerarquico);
				bloqueComponentesRepository.save(bloqueComponentes);
				log.debug("Se guardó correctamente el bloqueComponentes: {}", bloqueComponentes);
				
			//Guardando Componente
			for(ComponenteDTO componenteDTO : bloqueDTO.getComponentes()) {
				Componente componente = new Componente();
				componente.setContenido(componenteDTO.getContenido());
					componente.setTipoComponente(componenteDTO.getTipoComponente());
					componente.setBloqueComponentes(bloqueComponentes);
					componente.setVersion(componenteDTO.getVersion());
					componenteRepository.save(componente);
					log.debug("Se guardó correctamente el componente: {}", componente);
			}
		}
		
		//Guardando NivelesCurso
		NivelesCurso nivelesCurso = new NivelesCurso();
		Optional<Curso> curso = cursoRepository.findById(nivelJerarquicoDTO.getCursoId());
		if (curso.isPresent()) {
			log.debug("Curso: {}", curso.get());
			nivelesCurso.setCurso(curso.get());
			nivelesCurso.setNivelJerarquico(nivelJerarquico);
			nivelesCurso.setOrdenNivel(nivelJerarquicoDTO.getOrdenNivel());
			nivelesCursoRepository.save(nivelesCurso);
			log.debug("Se guardó correctamente nivelesCurso: {}", nivelesCurso);
		}else {
			throw new Exception("cursoId not found");
		}
		return nivelJerarquico;
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	public Page<NivelJerarquico> findAll(Pageable pageable) {
		
		return nivelJerarquicoRepository.findAll(pageable);
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<NivelJerarquico> findOne(Long id) {
		
		return nivelJerarquicoRepository.findById(id) ;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Long id) {
		
		nivelJerarquicoRepository.deleteById(id);
	}

}
