package org.constructor.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.constructor.domain.BloqueComponentes;
import org.constructor.domain.Componente;
import org.constructor.domain.Curso;
import org.constructor.domain.NivelJerarquico;
import org.constructor.domain.NivelesCurso;
import org.constructor.repository.BloqueComponentesRepository;
import org.constructor.repository.ComponenteRepository;
import org.constructor.repository.CursoRepository;
import org.constructor.repository.NivelJerarquicoRepository;
import org.constructor.repository.NivelesCursoRepository;
import org.constructor.response.NivelJerarquicoResponse;
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

// TODO: Auto-generated Javadoc
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
			bloqueComponentes.setOrden(bloqueDTO.getOrden());
			bloqueComponentes.setTipoBloqueComponentes(bloqueDTO.getTipoBloqueComponentes());
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
		Optional<Curso> curso = cursoRepository.findById(nivelJerarquicoDTO.getCursoId());
		if (curso.isPresent()) {
			log.debug("Curso: {}", curso.get());
			NivelesCurso nivelesCurso = new NivelesCurso();
			nivelesCurso.setCurso(curso.get());
			nivelesCurso.setNivelJerarquico(nivelJerarquico);
			nivelesCurso.setOrdenNivel(nivelJerarquicoDTO.getOrden());
			nivelesCursoRepository.save(nivelesCurso);
			log.debug("Se guardó correctamente nivelesCurso: {}", nivelesCurso);
		}else {
			throw new Exception("cursoId not found");
		}
		return nivelJerarquico;
	}
	
	
	 /**
 	 * Update nivel jerarquico.
 	 *
 	 * @param nivelJerarquicoDTO the nivel jerarquico DTO
 	 * @return the optional
 	 * @throws Exception the exception
 	 */
 	public Optional<NivelJerarquico> updateNivelJerarquico(NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception{
		 
		 
		return Optional.of(nivelJerarquicoRepository
		            .findById(nivelJerarquicoDTO.getNivelId()))
		            .filter(Optional::isPresent)
		            .map(Optional::get)
		            .map( nivel ->{
		            	nivel.getBloquesComponentes().stream().collect(Collectors.toSet()).forEach(
		            			bloque -> {
		            				log.debug("Eliminando bloques: {}", bloque.getId());
		            				bloqueComponentesRepository.deleteById(bloque.getId());
		            			}
		            			);
		            	nivelJerarquicoDTO.getBloquesComponentes().stream().forEach(
		            			bloqueDTO -> {
		            				if(!bloqueDTO.getComponentes().isEmpty()) {
		            				BloqueComponentes bloqueComponentes = new BloqueComponentes();
		            				bloqueComponentes.setTipoBloqueComponentes(bloqueDTO.getTipoBloqueComponentes());
		            				bloqueComponentes.setOrden(bloqueDTO.getOrden());
		            				log.debug("Bloque vaciooooo: {}", bloqueDTO.getComponentes());
		            				bloqueComponentes.setNivelJerarquico(nivel);
		            				bloqueComponentesRepository.save(bloqueComponentes);
		            				log.debug("Se guardó correctamente el bloqueComponentes: {}", bloqueComponentes);
		            				bloqueDTO.getComponentes().stream().forEach(
		            						componenteDTO -> {
		            							Componente componente = new Componente();
		            							componente.setVersion(componenteDTO.getVersion());
		            							componente.setTipoComponente(componenteDTO.getTipoComponente());
		            							componente.setContenido(componenteDTO.getContenido());
		            							componente.setBloqueComponentes(bloqueComponentes);
		            							componenteRepository.save(componente);
		            							log.debug("Se guardó correctamente el componente: {}", componente);
		            						}
		            						);
		            				}
		            				
		            			}
		            			);
		            	log.debug("Se guardó correctamente el componente: {}", nivel.getBloquesComponentes());
		            	return nivel;
		            		}
		            		);
		  
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
	public NivelJerarquicoResponse findOne(Long id) {
		
		NivelJerarquico nivelJerarquico = null;
		NivelJerarquicoResponse nivelResponse = new NivelJerarquicoResponse();
		Optional<NivelJerarquico> optionalNivelJerarquico = nivelJerarquicoRepository.findById(id);
		nivelJerarquico = optionalNivelJerarquico.get();
		nivelResponse.setBloquesComponentes(nivelJerarquico.getBloquesComponentes().stream().distinct().collect(Collectors.toList()));
		nivelResponse.setNombre(nivelJerarquico.getNombre());
		nivelResponse.setInformacionAdicional(nivelJerarquico.getInformacionAdicional());
		nivelResponse.setNivelId(nivelJerarquico.getId());
		nivelResponse.setTipo(nivelJerarquico.getTipo());
		log.debug("Response nivel {}", nivelResponse);
		return nivelResponse;
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
