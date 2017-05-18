package br.com.fucapi.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fucapi.dao.VeiculoDAO;
import br.com.fucapi.entity.Veiculo;
import br.com.fucapi.util.JPAUtil;


@SessionScoped
@ManagedBean
public class VeiculoAtivoMB {
	
	public List<Veiculo> listaVeiculoAtivo = new ArrayList<Veiculo>();
	
	public List<Veiculo> getListaVeiculoAtivo() {
		return listaVeiculoAtivo;
	}
	
	
	@PostConstruct
	public void carregarVeiculoAtivo(){
		EntityManager em = JPAUtil.getEntityManager();
		VeiculoDAO dao = new VeiculoDAO(em);
		listaVeiculoAtivo = dao.listarAtivos();
		em.close();
	}

}
