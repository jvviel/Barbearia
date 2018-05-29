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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Consumo;
import br.com.barbearia.repository.ConsumoRepository;
import br.com.barbearia.service.ConsumoService;

@RestController
@RequestMapping("/consumo")
public class ConsumoResource {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private ConsumoService consumoService;
	
	@GetMapping
	public List<Consumo> listarTodos() {
		return consumoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Consumo buscaPorId(@PathVariable Integer id) {
		return consumoService.buscarConsumoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Consumo> gravarConsumo(@Valid @RequestBody Consumo consumo) {
		Consumo consumoSalvo = consumoRepository.save(consumo);
		return ResponseEntity.status(HttpStatus.CREATED).body(consumoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Consumo> alterarConsumo(@Valid @RequestBody Consumo consumo,
			@PathVariable Integer id) {
		Consumo consumoSalvo = consumoService.atualizarConsumo(consumo, id);
		
		return ResponseEntity.ok().body(consumoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerConsumo(@PathVariable Integer id) {
		consumoRepository.delete(id);
	}
	
}
