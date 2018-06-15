package br.com.barbearia.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.PontosProduto;
import br.com.barbearia.repository.PontosProdutoRepository;

@Service
public class PontosProdutoService {

	@Autowired
	private PontosProdutoRepository pontosProdutoRepository;
	
	public PontosProduto buscarPontosPeloId(Integer id) {
		PontosProduto pontos = pontosProdutoRepository.findOne(id);
		if(pontos == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pontos;
	}
	
	public PontosProduto atualizarPontosProduto(Integer id, PontosProduto pontos) {
		PontosProduto pontosSalvo = buscarPontosPeloId(id);
		BeanUtils.copyProperties(pontos, pontosSalvo, "id");
		return pontosProdutoRepository.save(pontosSalvo);
	}
}
