package kr.co.kgc.temp.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class DatabaseConfig {
  
  @Bean(name = "dataSource")
  @Qualifier("dataSource")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource mysqlDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}