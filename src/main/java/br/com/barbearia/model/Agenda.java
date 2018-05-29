package br.com.barbearia.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_operacao")
	private String dataOperacao;
	
	@Column(name = "data_agendamento")
	private String dataAgendamento;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_horario")
	private Horarios horario;
	
	private String confirmado;
	
	@ManyToMany
	@JoinTable(name = "agenda_itens", joinColumns = 
	@JoinColumn(name = "id_agenda"), inverseJoinColumns = 
    @JoinColumn(name = "id_servico"))
	private List<Servico> servicos;
	
	public Agenda() {
		dataOperacao = getLocalDate();
	}
	
	public String getLocalDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Horarios getHorario() {
		return horario;
	}

	public void setHorario(Horarios horario) {
		this.horario = horario;
	}
	
	public String getConfirmado() {
		return confirmado;
	}
	
	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
