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

import br.com.barbearia.model.PontosProduto;
import br.com.barbearia.repository.PontosProdutoRepository;
import br.com.barbearia.service.PontosProdutoService;

@RestController
@RequestMapping("/pontos")
public class PontosProdutoResource {

	@Autowired
	private PontosProdutoRepository pontosProdutoRepository;
	
	@Autowired
	private PontosProdutoService pontosProdutoService;
	
	@GetMapping
	public List<PontosProduto> listarTodos() {
		return pontosProdutoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public PontosProduto buscarPorId(@PathVariable Integer id) {
		return pontosProdutoService.buscarPontosPeloId(id);
	}
	
	@PostMapping
	public ResponseEntity<PontosProduto> gravarPontos(@RequestBody PontosProduto pontos) {
		PontosProduto pontosSalvo = pontosProdutoRepository.save(pontos);
		return ResponseEntity.status(HttpStatus.CREATED).body(pontosSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PontosProduto> alterarPontos(@PathVariable Integer id, 
			@RequestBody PontosProduto pontos) {
		PontosProduto pontosSalvo = pontosProdutoService.atualizarPontosProduto(id, pontos);
		return ResponseEntity.ok().body(pontosSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void removerPontos(@PathVariable Integer id) {
		pontosProdutoRepository.delete(id);
	}
}
