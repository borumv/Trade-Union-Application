package backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
@Configuration
@EnableJpaRepositories(basePackages = "backend.persist.repositories", entityManagerFactoryRef = "managerFactory", transactionManagerRef = "transactionManager")
public class DataSourceConfig {
    @Autowired
    public DataSource ds;
    @Primary
    @Bean(name = "managerFactory")
    public LocalContainerEntityManagerFactoryBean managerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(ds).packages("backend.persist.*", "backend").build();
    }
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("managerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }





}