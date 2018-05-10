package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Tipo;
import br.com.barbearia.repository.TipoRepository;

@Service
public class TipoService {

	
	@Autowired
	private TipoRepository tipoRepository;
	
	public Tipo buscarTipoPorId(Integer id) {
		Tipo tipo = tipoRepository.findOne(id);
		if(tipo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return tipo;
	}
	
	public Tipo atualizarTipo(Integer id, Tipo tipo) {
		Tipo tipoSalvo = buscarTipoPorId(id);
		BeanUtils.copyProperties(tipo, tipoSalvo, "id");
		
		return tipoRepository.save(tipoSalvo);
	}
}
