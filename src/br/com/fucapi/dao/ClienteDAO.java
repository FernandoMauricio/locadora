package br.com.fucapi.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.fucapi.entity.Cliente;

@SuppressWarnings("unchecked")
public class ClienteDAO {
	
	
private EntityManager entityManager;
	
	public ClienteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Cliente cliente){
		entityManager.persist(cliente);
	}
	public void alterar(Cliente cliente){
		entityManager.merge(cliente);
	}
	public void excluir(Cliente cliente){
		entityManager.remove(entityManager.merge(cliente));
	}
	
	public Cliente consultar(Long id){
		return entityManager.getReference(Cliente.class, id);
	}
	
	public List<Cliente> listar(){
		String jpql = "Select p from Cliente p order by nome";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
	


