package br.com.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.PontosProduto;
import br.com.barbearia.model.Servico;

public interface PontosProdutoRepository extends JpaRepository<PontosProduto, Integer>{

	public PontosProduto findByServico(Servico servico);
}
