package br.com.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer>{

	public List<Agenda> findByDataAgendamento(String dataAgendamento);
}
