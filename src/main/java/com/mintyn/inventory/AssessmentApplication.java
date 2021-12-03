package com.mintyn.inventory;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.mintyn.inventory.api.test.IntegrationTest;

@SpringBootApplication
public class AssessmentApplication extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {
	
	public static void main(String[] args) {

		 SpringApplication.run(AssessmentApplication.class, args);
		 
		 IntegrationTest integrationTest = new IntegrationTest();		 
		 integrationTest.createProduct();
		 integrationTest.getProductListPaginated();
		 integrationTest.updateProduct();
		 integrationTest.orderProduct();
		 
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AssessmentApplication.class);
	}
	
	@Bean
	@Autowired
    public HibernateTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }
}

