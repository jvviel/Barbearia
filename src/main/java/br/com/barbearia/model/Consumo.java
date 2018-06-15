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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consumo")
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_consumo")
	private String dataConsumo;
	
	@Column(name = "valor_total")
	private Double valorTotal;
	
	private String pago;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(name = "consumo_itens", joinColumns = 
	@JoinColumn(name = "id_consumo"), inverseJoinColumns = 
    @JoinColumn(name = "id_servico"))
	private List<Servico> servicos;
	
	private Integer pontuacao;
	
	@Column(name = "pontuacao_usada")
	private Integer pontuacaoUsada;
	
	public Consumo() {
		this.dataConsumo = getLocalDate();
		this.pontuacao = 0;
		this.pontuacaoUsada = 0;
		this.pago = "N";
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

	public String getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(String dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Integer getPontuacaoUsada() {
		return pontuacaoUsada;
	}

	public void setPontuacaoUsada(Integer pontuacaoUsada) {
		this.pontuacaoUsada = pontuacaoUsada;
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
		Consumo other = (Consumo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
