package br.com.barbearia.resource;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Agenda;
import br.com.barbearia.model.Horarios;
import br.com.barbearia.repository.AgendaRepository;
import br.com.barbearia.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaResource {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping
	public List<Agenda> listarTodos() {
		return agendaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Agenda buscaPorId(@PathVariable Integer id) {
		return agendaService.buscarAgendaPorId(id);
	}
	
	@GetMapping("/horarios/{dataAgendamento}")
	public Set<Horarios> listarHorarios(@PathVariable String dataAgendamento) {
		return agendaService.consultarHorarioDisponivel(dataAgendamento);
	}
	
	@PostMapping
	public ResponseEntity<Agenda> gravarAgenda(@RequestBody Agenda agenda) {
		Agenda agendaSalva = agendaRepository.save(agenda);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Agenda> alterarAgenda(@PathVariable Integer id,
			@RequestBody Agenda agenda) {
		Agenda agendaSalva = agendaService.atualizarAgenda(id, agenda);
		
		return ResponseEntity.ok().body(agendaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerAgenda(@PathVariable Integer id) {
		agendaRepository.delete(id);
	}
}
