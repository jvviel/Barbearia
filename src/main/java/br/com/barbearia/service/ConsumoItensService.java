package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.ConsumoItens;
import br.com.barbearia.repository.ConsumoItensRepository;

@Service
public class ConsumoItensService {

	@Autowired
	private ConsumoItensRepository consumoItensRepository;
	
	public ConsumoItens buscarConsumoItensPorId(Integer id) {
		ConsumoItens consumo = consumoItensRepository.findOne(id);
		if(consumo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return consumo;
	}
	
	public ConsumoItens atualizarConsumoItens(Integer id, ConsumoItens consumo) {
		ConsumoItens consumoSalvo = buscarConsumoItensPorId(id);
		BeanUtils.copyProperties(consumo, consumoSalvo, "id");
		
		return consumoItensRepository.save(consumoSalvo);
	}
}
