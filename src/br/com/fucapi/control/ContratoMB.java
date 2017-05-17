package br.com.fucapi.control;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fucapi.dao.ClienteDAO;
import br.com.fucapi.dao.ContratoDAO;
import br.com.fucapi.dao.VeiculoDAO;
import br.com.fucapi.entity.Cliente;
import br.com.fucapi.entity.Contrato;
import br.com.fucapi.entity.Veiculo;
import br.com.fucapi.util.JPAUtil;

@ViewScoped
@ManagedBean
public class ContratoMB {
	
private Contrato contrato = new Contrato();

	private Long cliID;
	private Long veiID;

	
		public Long getCliID() {
		return cliID;
	}
	
	public void setCliID(Long cliID) {
		this.cliID = cliID;
	}
	
	public Long getVeiID() {
		return veiID;
	}
	
	public void setVeiID(Long veiID) {
		this.veiID = veiID;
	}

	public Contrato getContrato() {
		return contrato;
	}
	
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	

	public List<Contrato> listaContrato = new ArrayList<Contrato>();
	
	public List<Contrato> getListaContrato() {
		return listaContrato;
	}	


	@PostConstruct
	public void carregarContrato(){
		EntityManager em = JPAUtil.getEntityManager();
		ContratoDAO dao = new ContratoDAO(em);
		listaContrato = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		ContratoDAO dao = new ContratoDAO(em);
		em.getTransaction().begin();
		dao.excluir(contrato);
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Registro foi excluído!!!"));
		
		carregarContrato();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		ContratoDAO dao = new ContratoDAO(em);
		em.getTransaction().begin();
		
		ClienteDAO clidao = new ClienteDAO(em);
		Cliente cliente = clidao.consultar(cliID);
		contrato.setCliente(cliente);
		
		VeiculoDAO veidao = new VeiculoDAO(em);
		Veiculo veiculo = veidao.consultar(veiID);
		contrato.setVeiculo(veiculo);
		
		
		if(contrato.getNumContrato()!=null){
			dao.alterar(contrato);
		}else{
			dao.cadastrar(contrato);
		}
		em.getTransaction().commit();
		em.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( null, new FacesMessage( "Cadastro realizado com sucesso!!!"));

		contrato  = new Contrato();
		carregarContrato();
	}
	

}
