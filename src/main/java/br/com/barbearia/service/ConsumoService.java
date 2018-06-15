package br.com.barbearia.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barbearia.model.Consumo;
import br.com.barbearia.model.PontosProduto;
import br.com.barbearia.model.Servico;
import br.com.barbearia.model.Usuario;
import br.com.barbearia.repository.ConsumoRepository;
import br.com.barbearia.repository.PontosProdutoRepository;

@Service
public class ConsumoService {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PontosProdutoRepository pontosProdutoRepository;
	
	public Consumo buscarConsumoPorId(Integer id) {
		
		Consumo consumo = consumoRepository.findOne(id);
		if(consumo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return consumo;
	}
	
	public Consumo atualizarConsumo(Consumo consumo, Integer id) {
		Consumo consumoSalvo = buscarConsumoPorId(id);
		BeanUtils.copyProperties(consumo, consumoSalvo, "id");
		
		return consumoRepository.save(consumoSalvo);
	}
	
	public void calcularTotalConsumoETotalPontuacao(Consumo consumo) {
		Double total = 0d;
		Integer totalPontuacao = 0;
		List<Servico> servicos = consumo.getServicos();
		for (Servico servico : servicos) {
			System.out.println(servico.getDescricao());
			total += servico.getValor();
			totalPontuacao += servico.getPontuacao();
		}
		consumo.setPontuacao(totalPontuacao);
		consumo.setValorTotal(total);
	}
	
	public Consumo utilizarPontuacaoAcumulada(Consumo consumo) {
		Usuario usuario = consumo.getUsuario();
		List<Servico> servicos = consumo.getServicos();
		for (Servico servico : servicos) {
			PontosProduto pontos = pontosProdutoRepository.findByServico(servico);
			if(usuario.getPontuacao() >= pontos.getPontos()) {
				consumo.setValorTotal(consumo.getValorTotal() - servico.getValor());
				usuario.setPontuacao(usuario.getPontuacao() - pontos.getPontos());
				consumo.setPontuacaoUsada(consumo.getPontuacaoUsada() + pontos.getPontos());
				usuarioService.atualizarUsuario(usuario, usuario.getId());
			}
		}
		return consumo;
	}
	
	public Consumo finalizarConsumo(Consumo consumo) {
		Usuario usuario = consumo.getUsuario();
		usuario.setPontuacao(usuario.getPontuacao() + consumo.getPontuacao());
		usuarioService.atualizarUsuario(usuario, usuario.getId());
		return consumo;
	}
}
