package com.dteja.DBConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dteja.models.Category;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
public class DBConfiguration 
{
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl(" jdbc:h2:~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		LocalSessionFactoryBuilder localsessionFactory=new LocalSessionFactoryBuilder(getH2DataSource());
		localsessionFactory.addProperties(hibernateProperties);
		
		localsessionFactory.addAnnotatedClass(Category.class);
		
		SessionFactory sessionFactory=localsessionFactory.buildSessionFactory();
		
		return sessionFactory;
	}
	
	@Bean(name="txManager")
	public HibernateTransactionManager getHibernateTransactionmanager(SessionFactory sessionFactory)
	{
		return new HibernateTransactionManager(sessionFactory);
	}
}
