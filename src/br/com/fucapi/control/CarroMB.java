package br.com.fucapi.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fucapi.dao.CarroDAO;
import br.com.fucapi.entity.Carro;
import br.com.fucapi.util.JPAUtil;



@ViewScoped
@ManagedBean
public class CarroMB {

	private Carro carro = new Carro();
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	

	public List<Carro> listaCarro = new ArrayList<Carro>();
	
	public List<Carro> getListaCarro() {
		return listaCarro;
	}	


	@PostConstruct
	public void carregarProdutos(){
		EntityManager em = JPAUtil.getEntityManager();
		CarroDAO dao = new CarroDAO(em);
		listaCarro = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		CarroDAO dao = new CarroDAO(em);
		em.getTransaction().begin();
		dao.excluir(carro);
		em.getTransaction().commit();
		em.close();
		carregarProdutos();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		CarroDAO dao = new CarroDAO(em);
		em.getTransaction().begin();
		carro.setAnoFabricacao(Calendar.getInstance());
		if(carro.getId()!=null){
			dao.alterar(carro);
		}else{
			dao.cadastrar(carro);
		}
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Cadastro realizado com sucesso!!!"));
		
		carro  = new Carro();
		carregarProdutos();
	}
}
