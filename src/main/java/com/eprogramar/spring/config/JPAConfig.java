package com.eprogramar.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.eprogramar.spring" })
@PropertySource("classpath:jdbc.properties")
public class JPAConfig {

	/**
	 * Properties load from: src/main/resources/jdbc.properties
	 * Important = Need create Bean PropertySourcesPlaceholderConfigurer: 
	 * @Bean
	 * public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
	 *	 return new PropertySourcesPlaceholderConfigurer();
	 * }
	 * 
	 */
	 @Value("${jdbc.driver}")
	 private String driverClassName;
	 @Value("${jdbc.url}")
	 private String jdbcURL;
	 @Value("${jdbc.username}")
	 private String userName;
	 @Value("${jdbc.password}")
	 private String password;
    
    @Bean
	 public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	 }
    
	@Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName( this.driverClassName );
        dataSource.setUrl( this.jdbcURL );
        dataSource.setUsername( this.userName );
        dataSource.setPassword( this.password );
        
        return dataSource;
    } 
	
	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource( this.dataSource() );
	    em.setPackagesToScan( new String[]{ "com.eprogramar.spring.models" } );

	    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter( vendorAdapter );
	    em.setJpaProperties( this.additionalProperties() );
	    
	    return em;
	}
	
    @Bean(name = "transaction")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
    	JpaTransactionManager transactionManager = new JpaTransactionManager();
    	transactionManager.setEntityManagerFactory(emf);
    	return transactionManager;
    }
	
	private Properties additionalProperties() {
		   Properties properties = new Properties();
		   properties.setProperty( "hibernate.hbm2ddl.auto", "update" );
		   properties.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect" );
		   properties.setProperty( "hibernate.show_sql", "true" );
		   return properties;
		}
	
}
