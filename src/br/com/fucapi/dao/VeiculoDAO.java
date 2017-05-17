package br.com.fucapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fucapi.entity.Veiculo;

@SuppressWarnings("unchecked")
public class VeiculoDAO {
	
private EntityManager entityManager;
	
	public VeiculoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Veiculo veiculo){
		entityManager.persist(veiculo);
	}
	public void alterar(Veiculo veiculo){
		entityManager.merge(veiculo);
	}
	public void excluir(Veiculo veiculo){
		entityManager.remove(entityManager.merge(veiculo));
	}
	
	public Veiculo consultar(Long id){
		return entityManager.getReference(Veiculo.class, id);
	}
	
	public List<Veiculo> listar(){
		String jpql = "Select p from Veiculo p  order by fabricante";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	public List<Veiculo> listarAtivos(){
		String jpql = "Select p from Veiculo p where situacaoVeiculo='Regular' order by fabricante";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
}
