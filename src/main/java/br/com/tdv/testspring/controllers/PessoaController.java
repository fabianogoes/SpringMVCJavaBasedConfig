package br.com.tdv.testspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@RequestMapping
	public String get(){
		return "pessoa";
	}
	
}
