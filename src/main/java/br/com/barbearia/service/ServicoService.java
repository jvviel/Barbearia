package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Servico;
import br.com.barbearia.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	public Servico buscarServicoPorId(Integer id) {
		Servico servico = servicoRepository.findOne(id);
		if(servico == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return servico;
	}

	public Servico atualizarServico(Integer id, Servico servico) {
		
		Servico servicoSalvo = buscarServicoPorId(id);
		BeanUtils.copyProperties(servico, servicoSalvo, "id");
		
		return servicoRepository.save(servicoSalvo);
	}
}
