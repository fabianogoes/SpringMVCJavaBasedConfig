package com.eprogramar.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eprogramar.spring.models.Pessoa;
import com.eprogramar.spring.repositories.PessoaRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa entity){
		return this.pessoaRepository.save(entity);
	}

	public List<Pessoa> findAll() {
		return (List<Pessoa>) this.pessoaRepository.findAll();
	}

	public Pessoa findOne(Long id) {
		return this.pessoaRepository.findOne(id);
	}

	public void remove(Long id) {
		this.pessoaRepository.delete(id);
	}
	
}
