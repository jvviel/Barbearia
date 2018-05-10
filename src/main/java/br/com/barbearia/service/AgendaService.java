package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Agenda;
import br.com.barbearia.repository.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	public Agenda buscarAgendaPorId(Integer id) {
		Agenda agenda = agendaRepository.findOne(id);
		if(agenda == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return agenda;
	}
	
	public Agenda atualizarAgenda(Integer id, Agenda agenda) {
		Agenda agendaSalva = buscarAgendaPorId(id);
		BeanUtils.copyProperties(agenda, agendaSalva, "id");
		
		return agendaRepository.save(agendaSalva);
	}
}
