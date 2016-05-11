package com.eprogramar.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eprogramar.spring.models.Pessoa;
import com.eprogramar.spring.services.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping
	public String get(Model model){
		model.addAttribute("pessoas", this.pessoaService.findAll());
		return "pessoa";
	}
	
	@RequestMapping(value="/{id}")
	public String put(@PathVariable("id") Long id, Model model){
		model.addAttribute("pessoa", this.pessoaService.findOne(id));
		model.addAttribute("pessoas", this.pessoaService.findAll());
		return "pessoa";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id){
		this.pessoaService.remove(id);
		return "redirect:/pessoa";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(Pessoa pessoa){
		this.pessoaService.save(pessoa);
		return "redirect:/pessoa";
	}
	
}
