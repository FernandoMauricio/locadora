package br.com.fucapi.control;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import br.com.fucapi.dao.ClienteDAO;
import br.com.fucapi.entity.Cliente;
import br.com.fucapi.util.JPAUtil;




@SessionScoped
@ManagedBean
public class ClienteMB {

	private Cliente cliente = new Cliente();
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public List<Cliente> listaCliente = new ArrayList<Cliente>();
	
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}	


	@PostConstruct
	public void carregarCliente(){
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDAO dao = new ClienteDAO(em);
		listaCliente = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDAO dao = new ClienteDAO(em);
		em.getTransaction().begin();
		dao.excluir(cliente);
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Registro foi excluído!!!"));
		
		carregarCliente();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDAO dao = new ClienteDAO(em);
		em.getTransaction().begin();
		
		if(cliente.getId()!=null){
			dao.alterar(cliente);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage( null, new FacesMessage( "Cadastro foi alterado!!!"));
		}else{
			dao.cadastrar(cliente);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage( null, new FacesMessage( "Cadastro realizado com sucesso!!!"));
		}
		em.getTransaction().commit();
		em.close();

		cliente  = new Cliente();
		carregarCliente();
	}
}
