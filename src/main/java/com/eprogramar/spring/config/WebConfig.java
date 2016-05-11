package com.eprogramar.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Substitui o arquivo dispatcher-servlet.xml do projeto configurado com XMLs!
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.eprogramar.spring.controllers", "com.eprogramar.spring.repositories" })
public class WebConfig extends WebMvcConfigurerAdapter  {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Substitui o seguinte trecho:
	 * 
	 * <bean id="jspViewResolver" class=
	 * "org.springframework.web.servlet.view.InternalResourceViewResolver"
	 * p:prefix="/WEB-INF/view/" p:suffix=".jsp" />
	 * 
	 * @return o view resolver
	 */
	@Bean(name = "jspViewResolver")
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}
	
}