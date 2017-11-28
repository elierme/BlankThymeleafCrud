package br.com.blank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@ComponentScan("br.com.blank") 
@Configuration
@EnableWebMvc
@Import(WebSecurityConfig.class)
public class ThymeleafConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/user/home").setViewName("user/home");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/403").setViewName("403");
	
		
		//cadastro
		registry.addViewController("/user/cadastro/alterar").setViewName("user/cadastro/alterar");
		registry.addViewController("/user/cadastro/incluir").setViewName("user/cadastro/incluir");
		registry.addViewController("/user/cadastro/listagem").setViewName("user/cadastro/listagem");
		registry.addViewController("/user/cadastro/visualizar").setViewName("user/cadastro/visualizar");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		//registry.addResourceHandler("/bootstrap/css/**").addResourceLocations("/bootstrap/css/");
	}

	@Bean(name = "templateResolver")
	public ServletContextTemplateResolver getTemplateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean(name = "templateEngine")
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());
		templateEngine.addDialect(GetSpringSecurityDialect());
		return templateEngine;
	}

	@Bean(name = "viewResolver")
	public ThymeleafViewResolver getViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(getTemplateEngine());
		viewResolver.setCharacterEncoding("UTF-8"); 
		viewResolver.setContentType("text/html; charset=UTF-8");
		return viewResolver;
	}
	
	@Bean
	public SpringSecurityDialect GetSpringSecurityDialect() {
	    return new SpringSecurityDialect();
	}
}