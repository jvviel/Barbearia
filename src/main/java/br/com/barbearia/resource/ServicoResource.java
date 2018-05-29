package br.com.barbearia.resource;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Servico;
import br.com.barbearia.repository.ServicoRepository;
import br.com.barbearia.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping
	public List<Servico> listarTodos() {
		return servicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Servico buscaPorId(@PathVariable Integer id) {
		return servicoService.buscarServicoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Servico> gravarServico(@Valid @RequestBody Servico servico) {
		Servico servicoSalvo = servicoRepository.save(servico);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Servico> alterarServico(@Valid @PathVariable Integer id,
			@RequestBody Servico servico) {
		Servico servicoAtualizar = servicoService.atualizarServico(id, servico);
		
		return ResponseEntity.ok().body(servicoAtualizar);
	}
	
	@DeleteMapping("/{id}")
	public void removerServico(@PathVariable Integer id) {
		servicoRepository.delete(id);
	}
}
