package br.com.barbearia.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Agenda;
import br.com.barbearia.model.Horarios;
import br.com.barbearia.repository.AgendaRepository;
import br.com.barbearia.repository.HorarioRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private HorarioRepository horarioRepository;
	
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
	
	public Set<Horarios> consultarHorarioDisponivel(String dataAgendamento) {
		List<Horarios> horarios = horarioRepository.findAll();
		Set<Horarios> horariosDisponiveis = new HashSet<>();
		List<Agenda> agendamentos = agendaRepository.findByDataAgendamento(dataAgendamento);
		for(Agenda agenda : agendamentos) {
			for (Horarios horario : horarios) {
				if(agenda.getHorario().getHorario() != horario.getHorario()) {
					horariosDisponiveis.add(horario);
				}
			}
		}
		return horariosDisponiveis;
		}
}
