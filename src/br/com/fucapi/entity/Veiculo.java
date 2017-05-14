package br.com.fucapi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the veiculos database table.
 * 
 */
@Entity
@Table(name="veiculos")
@NamedQuery(name="Veiculo.findAll", query="SELECT v FROM Veiculo v")
public class Veiculo  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="ano_fabricacao")
	private String anoFabricacao;

	private String cor;

	private String fabricante;

	@Column(name="img_veiculo")
	private String imgVeiculo;

	private String modelo;

	private String placa;

	private String renavam;

	@Column(name="situacao_veiculo")
	private String situacaoVeiculo;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	private Categoria categoria;

	public Veiculo() {
	}

	public String getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getImgVeiculo() {
		return this.imgVeiculo;
	}

	public void setImgVeiculo(String imgVeiculo) {
		this.imgVeiculo = imgVeiculo;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavam() {
		return this.renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getSituacaoVeiculo() {
		return this.situacaoVeiculo;
	}

	public void setSituacaoVeiculo(String situacaoVeiculo) {
		this.situacaoVeiculo = situacaoVeiculo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}