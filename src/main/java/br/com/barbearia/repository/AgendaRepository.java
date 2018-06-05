package br.com.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Agenda;
import br.com.barbearia.model.Servico;
import br.com.barbearia.model.Usuario;

public interface AgendaRepository extends JpaRepository<Agenda, Integer>{

	public List<Agenda> findByDataAgendamento(String dataAgendamento);
	
	public List<Agenda> findByDataAgendamentoBetween(String dataInicio, String dataFinal);
	
	public List<Agenda> findByUsuario(Usuario usuario);
	
	public List<Agenda> findByServicosAndDataAgendamentoBetween(Servico servico, String dataInicio, String dataFinal);
}
