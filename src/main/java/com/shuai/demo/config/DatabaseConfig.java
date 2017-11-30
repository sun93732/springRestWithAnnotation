package com.shuai.demo.config;


import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"com.shuai.demo"})
public class DatabaseConfig
{

  @Resource
  private DatabaseProperties dbProps;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
    throws Exception
  {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(new String[] { this.dbProps.getPackageToScan() });

    Properties hibProps = new Properties();
    hibProps.setProperty("hibernate.dialect", this.dbProps.getDialect());
    hibProps.setProperty("hibernate.default_schema", this.dbProps.getSchema());

    if (this.dbProps.getBatchSize() == null)
      hibProps.setProperty("hibernate.jdbc.batch_size", "25");
    else {
      hibProps.setProperty("hibernate.jdbc.batch_size", this.dbProps
        .getBatchSize().toString());
    }
    hibProps.setProperty("hibernate.order_inserts", "true");
    hibProps.setProperty("hibernate.order_updates", "true");
    hibProps.setProperty("hibernate.hbm2ddl.auto", "true");
    hibProps.setProperty("hibernate.show_sql", "true");

    if (this.dbProps.getEnableHazelcast()) {
      hibProps.put("hibernate.cache.use_second_level_cache", 
        Boolean.valueOf(this.dbProps
        .getEnableHazelcast()));
      hibProps.put("hibernate.cache.use_query_cache", 
        Boolean.valueOf(this.dbProps
        .getHazelcastQueryCache()));
      hibProps.put("hibernate.cache.use_minimal_puts", 
        Boolean.valueOf(this.dbProps
        .getHazelcastUseMinimalPuts()));
      hibProps.put("hibernate.cache.region.factory_class", this.dbProps
        .getHazelcastCacheRegionFactory());
      hibProps.put("hibernate.cache.hazelcast.use_native_client", 
        Boolean.valueOf(this.dbProps
        .getHazelcastUseNativeClient()));
      hibProps.put("hibernate.cache.hazelcast.native_client_address", this.dbProps
        .getHazelcastNativeClientAddress());
      hibProps.put("hibernate.cache.hazelcast.native_client_group", this.dbProps
        .getHazelcastNativeClientGroup());
      hibProps.put("hibernate.cache.hazelcast.native_client_password", this.dbProps
        .getHazelcastNativeClientPassword());
    }

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(hibProps);

    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);

    return transactionManager;
  }
  @Bean
  public DataSource dataSource() throws Exception {
	 //datasource will be consumed by entitymanager
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(this.dbProps.getDriver());
    ds.setUrl(this.dbProps.getUrl());
    ds.setUsername(this.dbProps.getUser());
    ds.setPassword(this.dbProps.getPassword());

    ds.setInitialSize(this.dbProps.getInitialPoolSize().intValue());
    ds.setMinIdle(this.dbProps.getMinPoolSize().intValue());
    ds.setMaxActive(this.dbProps.getMaxPoolSize().intValue());

    return ds;
  }
}