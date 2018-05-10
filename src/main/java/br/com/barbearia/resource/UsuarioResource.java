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

import br.com.barbearia.model.Usuario;
import br.com.barbearia.repository.UsuarioRepository;
import br.com.barbearia.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario buscaPorId(@PathVariable Integer id) {
		return usuarioService.buscarUsuarioPeloId(id);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> gravarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterarUsuario(@PathVariable Integer id,
			@RequestBody Usuario usuario) {
		
		Usuario usuarioSalvo = usuarioService.atualizarUsuario(usuario, id);
		return ResponseEntity.ok().body(usuarioSalvo);
	}

	@DeleteMapping("/{id}")
	public void removerUsuario(@PathVariable Integer id) {
		
		usuarioRepository.delete(id);
	}
}