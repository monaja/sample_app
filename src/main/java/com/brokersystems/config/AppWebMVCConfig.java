package com.brokersystems.config;

import com.brokersystems.audit.SpringSecurityAuditorAware;
import com.brokersystems.server.datatables.DataTableRequestResolver;
import com.brokersystems.setups.model.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.apache.commons.dbcp.BasicDataSource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.brokersystems" })
@EnableJpaRepositories(basePackages = { "com.brokersystems" })
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableSpringDataWebSupport
@EnableTransactionManagement
@PropertySource({"classpath:config.properties","classpath:mail.properties"})
public class AppWebMVCConfig extends WebMvcConfigurerAdapter {

	
	@Autowired
	Environment env;
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	  JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	  resolver.setPrefix("classpath:/reports/");
	  resolver.setSuffix(".jasper");
	  resolver.setReportDataKey("datasource");
	  resolver.setViewNames("rpt_*");
	  resolver.setViewClass(JasperReportsMultiFormatView.class);
	  resolver.setOrder(0);
	  return resolver;
	} 

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/pages/");
		bean.setSuffix(".jsp");
		bean.setOrder(2);
		return bean;
	} 
	@Bean
	public TilesConfigurer TilesConfigurer()
	{
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
		return tilesConfigurer;
	}
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver bean = new UrlBasedViewResolver();
		bean.setViewClass(TilesView.class);
		bean.setOrder(1);
		return bean;
	} 


	@Bean
	public static PropertyPlaceholderConfigurer placeHolderConfigurer() {
		PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
		bean.setLocation(new ClassPathResource("config.properties"));
		bean.setLocation(new ClassPathResource("mail.properties"));
		bean.setIgnoreUnresolvablePlaceholders(true);
		return bean;
	}



	@Bean()
	public DataSource dataSource() {

		String driverClassName = env.getProperty("jdbc.driverClassName");
		String databaseurl = env.getProperty("jdbc.databaseurl");
		String dbUsername = env.getProperty("jdbc.username");
		String dbPassword = env.getProperty("jdbc.password");

		BasicDataSource bean = new BasicDataSource();
		bean.setDriverClassName(driverClassName);
		bean.setUrl(databaseurl);
		bean.setUsername(dbUsername);
		bean.setPassword(dbPassword);
		bean.setValidationQuery("SELECT 1");
		bean.setTestWhileIdle(true);
		bean.setTimeBetweenEvictionRunsMillis(1000000);
		return bean;
	}

	@Bean
	public Properties hibernateProperties() {
		String dialect = env.getProperty("jdbc.dialect");

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", dialect);
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");

		return hibernateProperties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory(DataSource dataSource,
			Properties hibernateProperties) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
				dataSource);
        sessionBuilder.scanPackages("com.brokersystems");
		sessionBuilder.addProperties(hibernateProperties);
		sessionBuilder.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		return sessionBuilder.buildSessionFactory();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.brokersystems.setups.model");
		em.setPersistenceUnitName("archPersistenceUnit");
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	

	@Bean
	public SpringSecurityAuditorAware springSecurityAuditorAware() {
		return new SpringSecurityAuditorAware();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder bean = new BCryptPasswordEncoder(10);
		return bean;
	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/images/");
		registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
		registry.addResourceHandler("/fonts/**")
				.addResourceLocations("/fonts/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:i18n/messages");
		resource.setDefaultEncoding("UTF-8");
		resource.setFallbackToSystemLocale(true);
		return resource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		localeResolver.setCookieName("locale");
		localeResolver.setCookieMaxAge(Integer.MAX_VALUE);
		return localeResolver;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(41943040);
	    return multipartResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	@Override
	public void configureMessageConverters(
			java.util.List<org.springframework.http.converter.HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
		converters.add(bean);
	}
	@Override
	public void addArgumentResolvers(
			java.util.List<org.springframework.web.method.support.HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new DataTableRequestResolver());
	}
	
	@Bean
    public JavaMailSender getMailSender(){
		String host = env.getProperty("mail.host");
		String port = env.getProperty("mail.port");
		String username = env.getProperty("sender.username");
		String password = env.getProperty("sender.password");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(new Integer(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
       // javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}