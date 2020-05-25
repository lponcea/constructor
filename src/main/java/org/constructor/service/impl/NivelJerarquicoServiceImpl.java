package org.constructor.service.impl;

import java.util.Optional;

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
		NivelesCurso nivelesCurso = new NivelesCurso();
		Optional<Curso> curso = cursoRepository.findById(nivelJerarquicoDTO.getCursoId());
		if (curso.isPresent()) {
			log.debug("Curso: {}", curso.get());
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
	public Optional<NivelJerarquico> updateNivelJerarquico(NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception {
			
		
		return Optional.of(nivelJerarquicoRepository
	            .findById(nivelJerarquicoDTO.getNivelId()))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(nivel -> {
	            	log.debug("Update nivel: {}", nivel);
	            	nivelJerarquicoDTO.getBloquesComponentes().stream().forEach(
	            			bloqueNuevo ->{
       							if(bloqueNuevo.getId() == null) {
    								log.debug("Se agrega un nuevo componente: {}", bloqueNuevo);
    								BloqueComponentes bloqueComponentes = new BloqueComponentes();
    								bloqueComponentes.setOrden(bloqueNuevo.getOrden());
    								bloqueComponentes.setTipoBloqueComponentes(bloqueNuevo.getTipoBloqueComponentes());
    								bloqueComponentes.setNivelJerarquico(nivel);
    								bloqueComponentesRepository.save(bloqueComponentes);
    								bloqueNuevo.getComponentes().stream().forEach(
    										componenteDTO -> {
    											Componente componente = new Componente();
    											componente.setTipoComponente(componenteDTO.getTipoComponente());
    											componente.setBloqueComponentes(bloqueComponentes);
    											componente.setVersion(componenteDTO.getVersion());
    											componente.setContenido(componenteDTO.getContenido());;
    											componenteRepository.save(componente);
    										}
    										);
    							}
	            				
	            			}
	            			);
	            	nivel.getBloquesComponentes().stream().forEach(
	            			bloque -> {
	            				nivelJerarquicoDTO.getBloquesComponentes().stream().forEach(
	            						bloquedto -> {
	            							if(bloque.getId().equals(bloquedto.getId())) {
	            								bloque.setOrden(bloquedto.getOrden());
	            								bloque.setTipoBloqueComponentes(bloquedto.getTipoBloqueComponentes());
	            								bloque.getComponentes().stream().forEach(
	            										componente -> {
	            											bloquedto.getComponentes().stream().forEach(
	            													componenteDTO -> {
	            														if(componente.getId().equals(componenteDTO.getId())) {
	            															componente.setContenido(componenteDTO.getContenido());
	            															componente.setVersion(componenteDTO.getVersion());
	            															componente.setTipoComponente(componenteDTO.getTipoComponente());
	            														}
	            													}
	            													);
	            										}
	            										);
	            							}
	 
	            						});
	            			});
	            	nivel.setNombre(nivelJerarquicoDTO.getNombre());
	            	nivel.setTipo(nivelJerarquicoDTO.getTipo());
	            	nivel.setInformacionAdicional(nivelJerarquicoDTO.getInformacionAdicional());
	            	
	            	Optional<Curso> curso = cursoRepository.findById(nivelJerarquicoDTO.getCursoId());
	            	Optional.of(nivelesCursoRepository.findById(nivelJerarquicoDTO.getNivelId()))
	            			.filter(Optional::isPresent)
	            			.map(Optional::get)
	            			.map( nvls -> { 
	            					nvls.setCurso(curso.get());
	            					nvls.setNivelJerarquico(nivel);
	            					nvls.setOrdenNivel(nivelJerarquicoDTO.getOrden());
	            					return nvls;
	            				});
	            	
	                log.debug("Changed Information for User: {}", nivel);
	                return nivel;
	            });
		
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
		
		NivelJerarquico nv;
		NivelJerarquicoResponse nivelResponse = new NivelJerarquicoResponse();
		Optional<NivelJerarquico> onv = nivelJerarquicoRepository.findById(id);
		nv = onv.get();
		nivelResponse.setBloquesComponentes(nv.getBloquesComponentes());
		nivelResponse.setNombre(nv.getNombre());
		nivelResponse.setInformacionAdicional(nv.getInformacionAdicional());
		nivelResponse.setNivelId(nv.getId());
		nivelResponse.setTipo(nv.getTipo());
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
