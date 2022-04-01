package br.com.spring.batch.exemple.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    private static final String PREFIX_APP_DATASOURCE = "app.datasource";
    private static final String PREFIX_SPRING_BATCH_DATASOURCE = "spring.datasource";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = PREFIX_SPRING_BATCH_DATASOURCE)
    public DataSource springDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "appDatasource")
    @ConfigurationProperties(prefix = PREFIX_APP_DATASOURCE)
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("appDatasource") DataSource datasource){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("data-2.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
