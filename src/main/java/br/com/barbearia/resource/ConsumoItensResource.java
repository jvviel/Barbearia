package br.com.barbearia.resource;

import java.util.List;

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

import br.com.barbearia.model.ConsumoItens;
import br.com.barbearia.repository.ConsumoItensRepository;
import br.com.barbearia.service.ConsumoItensService;

@RestController
@RequestMapping("/consumoItens")
public class ConsumoItensResource {

	@Autowired
	private ConsumoItensRepository consumoItensRepository;
	
	@Autowired
	private ConsumoItensService consumoService;
	
	@GetMapping
	public List<ConsumoItens> listarTodos() {
		return consumoItensRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ConsumoItens buscaPorId(@PathVariable Integer id) {
		return consumoService.buscarConsumoItensPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<ConsumoItens> gravarConsumo(@RequestBody ConsumoItens consumoItens) {
		ConsumoItens consumoSalvo = consumoItensRepository.save(consumoItens);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(consumoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ConsumoItens> alterarConsumo(@PathVariable Integer id, 
			@RequestBody ConsumoItens consumo) {
		ConsumoItens consumoSalvo = consumoService.atualizarConsumoItens(id, consumo);
		
		return ResponseEntity.ok().body(consumoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void removerConsumo(@PathVariable Integer id) {
		consumoItensRepository.delete(id);
	}
}
