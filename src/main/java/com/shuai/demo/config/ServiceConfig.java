package com.shuai.demo.config;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@PropertySource("classpath:service.properties")
public class ServiceConfig {
	protected final static String MEMBER_SCHEMA = "test";
	protected final static String MEMBER_PACKAGE = "com.shuai.demo.model";

	@Value("${hibernate.ds.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.ds.driver}")
	private String dsDriver;
	@Value("${hibernate.ds.url}")
	private String dsUrl;
	@Value("${hibernate.ds.user}")
	private String dsUser;
	@Value("${hibernate.ds.password}")
	private String dsPassword;
	@Value("${hibernate.ds.initalpoolsize}")
	private Integer dsInitialPoolSize;
	@Value("${hibernate.ds.minpoolsize}")
	private Integer dsMinPoolSize;
	@Value("${hibernate.ds.maxpoolsize}")
	private Integer dsMaxPoolSize;

	@Bean
	public DatabaseProperties databaseProperties() {
		DatabaseProperties props = new DatabaseProperties();
		props.setUrl(dsUrl);
		props.setUser(dsUser);
		props.setPassword(dsPassword);
		props.setInitialPoolSize(dsInitialPoolSize);
		props.setMinPoolSize(dsMinPoolSize);
		props.setMaxPoolSize(dsMaxPoolSize);
		props.setDialect(hibernateDialect);
		props.setDriver(dsDriver);
		props.setSchema(MEMBER_SCHEMA);
		props.setPackageToScan(MEMBER_PACKAGE);
		return props;
	}

	@Bean
	Validator beanValidation() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] {
				new ClassPathResource(ServiceConfig.class.getDeclaredAnnotation(PropertySource.class).value()[0]
						.replaceFirst("classpath:", "")) };
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(false);
		return pspc;
	}
}