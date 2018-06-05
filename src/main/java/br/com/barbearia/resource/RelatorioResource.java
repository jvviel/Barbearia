package br.com.barbearia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Agenda;
import br.com.barbearia.model.Consumo;
import br.com.barbearia.model.Servico;
import br.com.barbearia.model.Usuario;
import br.com.barbearia.repository.AgendaRepository;
import br.com.barbearia.repository.ConsumoRepository;
import br.com.barbearia.repository.ServicoRepository;
import br.com.barbearia.repository.UsuarioRepository;

@RestController
@RequestMapping("/relatorios")
public class RelatorioResource {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping("/agenda/{dataInicio}/{dataFinal}")
	public List<Agenda> relatorioAgendaPorPeriodo(@PathVariable String dataInicio,
			@PathVariable String dataFinal) {
		return agendaRepository.findByDataAgendamentoBetween(dataInicio, dataFinal);
	}
	
	@GetMapping("/vendas/{dataInicio}/{dataFinal}")
	public List<Consumo> relatorioVendasPorPeriodo(@PathVariable String dataInicio,
			@PathVariable String dataFinal) {
		return consumoRepository.findByDataConsumoBetween(dataInicio, dataFinal);
	}
	
	@GetMapping("/vendas/{idCliente}")
	public List<Consumo> relatorioVendasPorCliente(@PathVariable Integer idCliente) {
		Usuario usuario = usuarioRepository.findOne(idCliente);
		return consumoRepository.findByUsuario(usuario);
	}
	
	@GetMapping("/servico/{idServico}/{dataInicio}/{dataFinal}")
	public List<Consumo> relatorioVendasPorServico(@PathVariable String dataInicio,
			@PathVariable String dataFinal, @PathVariable Integer idServico) {
		Servico servico = servicoRepository.findOne(idServico);
		return consumoRepository.findByServicosAndDataConsumoBetween(servico, dataInicio, dataFinal);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public List<Agenda> relatorioAgendamentoPorCliente(@PathVariable Integer idCliente) {
		Usuario usuario = usuarioRepository.findOne(idCliente);
		return agendaRepository.findByUsuario(usuario);
	}
	
	@GetMapping("/agendaServico/{idServico}/{dataInicio}/{dataFinal}")
	public List<Agenda> relatorioAgendamentoPorServico(@PathVariable String dataInicio,
			@PathVariable String dataFinal, @PathVariable Integer idServico) {
		Servico servico = servicoRepository.findOne(idServico);
		return agendaRepository.findByServicosAndDataAgendamentoBetween(servico, dataInicio, dataFinal);
	}
	
	
	
	
	
	
	
	
}
