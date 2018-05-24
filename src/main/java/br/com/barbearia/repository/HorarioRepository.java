package br.com.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Horarios;

public interface HorarioRepository extends JpaRepository<Horarios, Long>{

}
