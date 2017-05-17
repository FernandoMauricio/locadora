package br.com.fucapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fucapi.entity.Categoria;


@SuppressWarnings("unchecked")
public class CategoriaDAO {

private EntityManager entityManager;
	
	public CategoriaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Categoria categoria){
		entityManager.persist(categoria);
	}
	public void alterar(Categoria categoria){
		entityManager.merge(categoria);
	}
	public void excluir(Categoria categoria){
		entityManager.remove(entityManager.merge(categoria));
	}
	
	public Categoria consultar(Long id){
		return entityManager.getReference(Categoria.class, id);
	}
	
	public List<Categoria> listar(){
		String jpql = "Select p from Categoria p order by id";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}
