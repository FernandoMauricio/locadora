package br.com.fucapi.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fucapi.dao.VeiculoDAO;
import br.com.fucapi.entity.Veiculo;
import br.com.fucapi.util.JPAUtil;



@ViewScoped
@ManagedBean
public class VeiculoMB{
	
	private Veiculo veiculo = new Veiculo();

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
	
	public List<Veiculo> getListaVeiculo() {
		return listaVeiculo;
	}	


	@PostConstruct
	public void carregarVeiculo(){
		EntityManager em = JPAUtil.getEntityManager();
		VeiculoDAO dao = new VeiculoDAO(em);
		listaVeiculo = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		VeiculoDAO dao = new VeiculoDAO(em);
		em.getTransaction().begin();
		dao.excluir(veiculo);
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Registro foi excluído!!!"));
		
		carregarVeiculo();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		VeiculoDAO dao = new VeiculoDAO(em);
		em.getTransaction().begin();
		if(veiculo.getId()!=null){
			dao.alterar(veiculo);
		}else{
			dao.cadastrar(veiculo);
		}
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Cadastro realizado com sucesso!!!"));

		veiculo  = new Veiculo();
		carregarVeiculo();
	}

}
