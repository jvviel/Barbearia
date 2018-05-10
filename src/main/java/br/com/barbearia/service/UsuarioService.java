package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Usuario;
import br.com.barbearia.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarUsuarioPeloId(Integer id) {
		Usuario usuario = usuarioRepository.findOne(id);
		if(usuario == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuario;
	}
	
	public Usuario atualizarUsuario(Usuario usuario, Integer id) {
		Usuario usuarioSalvo = buscarUsuarioPeloId(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usuarioRepository.save(usuarioSalvo);
	}
}
