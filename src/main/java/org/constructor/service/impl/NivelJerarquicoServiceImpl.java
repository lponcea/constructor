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
	
	private final Logger log = LoggerFactory.getLogger(NivelJerarquicoServiceImpl.class);
	
	 private final NivelJerarquicoRepository nivelJerarquicoRepository;
	 
	 @Autowired
	 private BloqueComponentesRepository bloqueComponentesRepository;
	 
	 @Autowired
	 private ComponenteRepository componenteRepository;
	 
	 @Autowired 
	 private TipoBloqueComponentesRepository tipoBloqueComponenteRepository;
	 
	 @Autowired 
	 private TipoComponenteRepository tipoComponenteRepository;
	 
	 @Autowired
	 private CursoRepository cursoRepository;
	 
	 @Autowired
	 private NivelesCursoRepository nivelesCursoRepository;
	 
	 
	 
	    public NivelJerarquicoServiceImpl(NivelJerarquicoRepository nivelJerarquicoRepository) {
	        this.nivelJerarquicoRepository = nivelJerarquicoRepository;
	    }

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
			Optional<TipoBloqueComponentes> tipoBloqueComponentes = tipoBloqueComponenteRepository.findById(bloqueDTO.getTipoBloqueComponentes());
			if(tipoBloqueComponentes.isPresent()) {
				log.debug(" TipoBloqueComponentes: {}", tipoBloqueComponentes);
				bloqueComponentes.setTipoBloqueComponentes(tipoBloqueComponentes.get());
				bloqueComponentes.setNivelJerarquico(nivelJerarquico);
				bloqueComponentesRepository.save(bloqueComponentes);
				log.debug("Se guardó correctamente el bloqueComponentes: {}", bloqueComponentes);
			}else {
				throw new Exception("tipoBloqueComponentesId not found");
			}
			//Guardando Componente
			for(ComponenteDTO componenteDTO : bloqueDTO.getComponentes()) {
				Componente componente = new Componente();
				componente.setContenido(componenteDTO.getContenido());
				Optional<TipoComponente> tipoComponente = tipoComponenteRepository.findById(componenteDTO.getTipoComponente());
				if (tipoComponente.isPresent()) {
					componente.setTipoComponente(tipoComponente.get());
					componente.setBloqueComponentes(bloqueComponentes);
					componenteRepository.save(componente);
					log.debug("Se guardó correctamente el componente: {}", componente);
				}else {
					throw new Exception("tipoComponenteId not found");
				}
			}
		}
		
		//Guardando NivelesCurso
		NivelesCurso nivelesCurso = new NivelesCurso();
		Optional<Curso> curso = cursoRepository.findById(nivelJerarquicoDTO.getCursoId());
		if (curso.isPresent()) {
			log.debug("Curso: {}", curso.get());
			nivelesCurso.setCurso(curso.get());
			nivelesCurso.setNivelJerarquico(nivelJerarquico);
			nivelesCurso.setOrdenNivel(0);
			nivelesCursoRepository.save(nivelesCurso);
			log.debug("Se guardó correctamente nivelesCurso: {}", nivelesCurso);
		}else {
			throw new Exception("cursoId not found");
		}
		return nivelJerarquico;
	}

	@Override
	public Page<NivelJerarquico> findAll(Pageable pageable) {
		
		return nivelJerarquicoRepository.findAll(pageable);
	}

	@Override
	public Optional<NivelJerarquico> findOne(Long id) {
		
		return nivelJerarquicoRepository.findById(id) ;
	}

	@Override
	public void delete(Long id) {
		
		nivelJerarquicoRepository.deleteById(id);
	}

}
