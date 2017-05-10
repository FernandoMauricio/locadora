package br.com.fucapi.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fucapi.entity.Carro;


@SuppressWarnings("unchecked")
public class CarroDAO {

	private EntityManager entityManager;
	
	public CarroDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Carro carro){
		entityManager.persist(carro);
	}
	public void alterar(Carro carro){
		entityManager.merge(carro);
	}
	public void excluir(Carro carro){
		entityManager.remove(entityManager.merge(carro));
	}
	
	public Carro consultar(Long id){
		return entityManager.getReference(Carro.class, id);
	}
	
	public List<Carro> listar(){
		String jpql = "Select p from Carro p order by fabricante";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
