package br.com.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Consumo;

public interface ConsumoRepository extends JpaRepository<Consumo, Integer>{

}
