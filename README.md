# SpringProjectWithoutXMLConfiguration

* File > New > Maven Project
* Check "Create a simple project"
* Configure pom.xml   
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

	<modelVersion>4.0.0</modelVersion>
	<groupId>br.com.tdv</groupId>
	<artifactId>testspring</artifactId>
	<name>TestSpring</name>
	<packaging>war</packaging>
	<version>1.0.20160419</version>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java-version>1.8</java-version>
		<org.springframework-version>4.2.5.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
		<org.hibernate-version>4.3.11.Final</org.hibernate-version>
		<junit-version>4.12</junit-version>
	</properties>

	<dependencies>
	
		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>

		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>		
		
		<!-- Others dependencies -->
  </dependencies>		
  
	<build>
		<finalName>testspring</finalName>
		<plugins>
		
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<compilerArgument>-Xlint:all</compilerArgument>
					<showWarnings>true</showWarnings>
					<showDeprecation>true</showDeprecation>
				</configuration>
			</plugin>
			
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.6</version>
				<configuration>
					<failOnMissingWebXml>false</failOnMissingWebXml>
				</configuration>
			</plugin>

		</plugins>
	</build>
	
</project>
```      

* Create Class "WebConfig" - equivalent "servlet-context.xml"     
```java   
package br.com.tdv.testspring.config;

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
@ComponentScan(basePackages = { "br.com.tdv.testspring.controllers", "br.com.tdv.testspring.repositories" })
public class WebConfig extends WebMvcConfigurerAdapter  {

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
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
```   

* Create class "WebAppInitializer" - equivalent "web.xml"   
```java    
package br.com.tdv.testspring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Grande parte das configurações dessa classe já estão feitas na classe abstrata que extendemos!
 * 
 * O que está configurado aqui é o que estaria no web.xml!
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


}
```   

### References   

[Spring MVC configurado sem nenhum XML][0]   
[Configuração de uma aplicação Spring através de código Java (sem XML)][1]   
[Complete example of a Spring MVC 3.2 project][2]
[Spring MVC without web.xml using WebApplicationInitializer][3]
[Spring sem XML. É possível?][4]
[Data access with JDBC][5]
[Annotation Type EnableTransactionManagement][6]
[Annotation Type Configuration][7]
[Oracle Pooling DataSource][8]
[XML Schema-based configuration][9]
[How to use JavaConfig to declare a JNDI datasource][10]



[0]: http://www.sitedoph.com.br/spring-mvc-configurado-sem-nenhum-xml/
[1]: http://www.klebermota.eti.br/2014/04/22/configuracao-de-uma-aplicacao-spring-atraves-de-codigo-java-sem-xml/
[2]: https://geowarin.wordpress.com/2013/01/23/complete-example-of-a-spring-mvc-3-2-project/
[3]: http://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
[4]: http://blog.caelum.com.br/spring-sem-xml/
[5]: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html
[6]: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/annotation/EnableTransactionManagement.html
[7]: http://docs.spring.io/autorepo/docs/spring/4.1.1.RELEASE/javadoc-api/org/springframework/context/annotation/Configuration.html
[8]: http://docs.spring.io/spring-data/jdbc/docs/current/reference/html/orcl.datasource.html
[9]: http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/xsd-config.html
[10]: http://forum.spring.io/forum/spring-projects/container/724356-how-to-use-javaconfig-to-declare-a-jndi-datasource
