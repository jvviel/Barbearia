package br.com.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Consumo;
import br.com.barbearia.model.Servico;
import br.com.barbearia.model.Usuario;

public interface ConsumoRepository extends JpaRepository<Consumo, Integer>{

	public List<Consumo> findByDataConsumoBetween(String dataInicio, String dataFinal);
	
	public List<Consumo> findByUsuario(Usuario usuario);
	
	public List<Consumo> findByServicosAndDataConsumoBetween(Servico servico,
			String dataInicio, String dataFinal);
}
