package com.eprogramar.spring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eprogramar.spring.models.Pessoa;

@Repository
public class PessoaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Pessoa save(Pessoa entity){
		if( entity.getId() != null && entity.getId() > 0 ){
			return this.entityManager.merge(entity);
		}
		this.entityManager.persist(entity);
		return entity;
	}

	public List<Pessoa> findAll() {
		return this.entityManager.createQuery("from Pessoa", Pessoa.class).getResultList();
	}

	public Pessoa findOne(Long id) {
		return this.entityManager.find(Pessoa.class, id);
	}

	public void remove(Long id) {
		Pessoa pessoa = this.findOne(id);		
		this.entityManager.remove(pessoa);
	}
	
}
