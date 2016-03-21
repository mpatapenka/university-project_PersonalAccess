package org.diploma.personalaccess.config;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Database configuration, also instantiating Hibernate parameters
 *
 * @author Maksim Patapenka
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.diploma.personalaccess.repository")
@PropertySource("classpath:db.properties")
@ComponentScan("org.diploma.personalaccess")
public class DatabaseConfig {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(DatabaseConfig.class);

    /**
     * Properties file holder. Configured by @PropertySource
     */
    @javax.annotation.Resource
    private Environment env;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/db/h2/create_database.sql")
                .addScript("/db/h2/populate_database.sql")
                .build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());

        return manager;
    }

    private Properties getHibernateProperties() {
        final String propertyFile = "hibernate.properties";

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFile);
            Properties properties = new Properties();
            properties.load(is);

            return properties;
        } catch (IOException e) {
            String message = "Can not find '" + propertyFile + "' in classpath.";
            log.error(message, e);
            throw new IllegalArgumentException(message, e);
        }
    }

}
