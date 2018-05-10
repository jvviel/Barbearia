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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Tipo;
import br.com.barbearia.repository.TipoRepository;
import br.com.barbearia.service.TipoService;

@RestController
@RequestMapping("/tipo")
public class TipoResource {

	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private TipoService tipoService;
	
	@GetMapping
	public List<Tipo> listarTodos() {
		return tipoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Tipo buscaPorId(@PathVariable Integer id) {
		return tipoService.buscarTipoPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Tipo> gravarTipo(@RequestBody Tipo tipo) {
		Tipo tipoSalvo = tipoRepository.save(tipo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tipo> alterarTipo(@PathVariable Integer id, @RequestBody Tipo tipo) {
		Tipo tipoSalvo = tipoService.atualizarTipo(id, tipo);
		
		return ResponseEntity.ok().body(tipoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerTipo(@PathVariable Integer id) {
		tipoRepository.delete(id);
	}
}
