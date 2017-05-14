package br.com.fucapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.fucapi.entity.Contrato;

@SuppressWarnings("unchecked")
public class ContratoDAO {
	
private EntityManager entityManager;
	
	public ContratoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Contrato contrato){
		entityManager.persist(contrato);
	}
	public void alterar(Contrato contrato){
		entityManager.merge(contrato);
	}
	public void excluir(Contrato contrato){
		entityManager.remove(entityManager.merge(contrato));
	}
	
	public Contrato consultar(Long id){
		return entityManager.getReference(Contrato.class, id);
	}
	
	public List<Contrato> listar(){
		String jpql = "Select p from Contrato p order by num_contrato";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}
