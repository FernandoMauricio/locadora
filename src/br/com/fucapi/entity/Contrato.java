package br.com.fucapi.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the contratos database table.
 * 
 */
@Entity
@Table(name="contratos")
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="num_contrato")
	private Long numContrato;


	@Temporal(TemporalType.DATE)
	@Column(name="data_devolucao")
	private Date dataDevolucao;

	@Temporal(TemporalType.DATE)
	@Column(name="data_retirada")
	private Date dataRetirada;

	@Column(name="situacao_contrato")
	private String situacaoContrato;


	@Column(name="quant_diaria")
	private double quantDiaria;

	@Column(name="valor_diaria")
	private double valorDiaria;


	@Column(name="valor_taxas")
	private double valorTaxas;

	@Column(name="valor_total")
	private String valorTotal;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="fk_clientes_id")
	private Cliente cliente;

	//bi-directional many-to-one association to Veiculo
	@ManyToOne
	@JoinColumn(name="fk_veiculos_id")
	private Veiculo veiculo;

	public Contrato() {
	}


	public Date getDataDevolucao() {
		return this.dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getDataRetirada() {
		return this.dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}


	public String getSituacaoContrato() {
		return this.situacaoContrato;
	}

	public void setSituacaoContrato(String situacaoContrato) {
		this.situacaoContrato = situacaoContrato;
	}

	public double getValorDiaria() {
		return this.valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}


	public double getValorTaxas() {
		return this.valorTaxas;
	}

	public void setValorTaxas(double valorTaxas) {
		this.valorTaxas = valorTaxas;
	}

	public String getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	


	public Long getNumContrato() {
		return numContrato;
	}



	public void setNumContrato(Long numContrato) {
		this.numContrato = numContrato;
	}
	
	
	public double getQuantDiaria() {
		return quantDiaria;
	}


	public void setQuantDiaria(double quantDiaria) {
		this.quantDiaria = quantDiaria;
	}




}