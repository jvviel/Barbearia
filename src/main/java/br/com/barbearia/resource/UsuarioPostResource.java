package br.com.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbearia.model.Usuario;
import br.com.barbearia.repository.UsuarioRepository;
import br.com.barbearia.service.UsuarioService;

@RestController
@RequestMapping("/usuarioPost")
public class UsuarioPostResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> gravarUsuario(@RequestBody Usuario usuario) {
		usuario.setSenha(usuarioService.encoderPassword(usuario.getSenha()));
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
}
