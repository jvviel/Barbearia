package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Consumo;
import br.com.barbearia.repository.ConsumoRepository;

@Service
public class ConsumoService {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	public Consumo buscarConsumoPorId(Integer id) {
		
		Consumo consumo = consumoRepository.findOne(id);
		if(consumo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return consumo;
	}
	
	public Consumo atualizarConsumo(Consumo consumo, Integer id) {
		Consumo consumoSalvo = buscarConsumoPorId(id);
		BeanUtils.copyProperties(consumo, consumoSalvo, "id");
		
		return consumoRepository.save(consumoSalvo);
	}
}
