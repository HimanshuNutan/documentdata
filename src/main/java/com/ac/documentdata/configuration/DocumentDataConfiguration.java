package com.ac.documentdata.configuration;

import com.ac.documentdata.exception.RestResponseErrorHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DocumentDataConfiguration {

  @Autowired
  private ApplicationProperties properties;

  @Bean
  public RestTemplate restTemplate() {

    return
            new RestTemplateBuilder()
                    .errorHandler(new RestResponseErrorHandler())
                    .build();
  }

  @Bean
  public DataSource getDataSource() {

    HikariConfig config = new HikariConfig();
    final ApplicationProperties.DB db = properties.getDb();
    config.setDriverClassName(db.getDriverclassname());
    config.setJdbcUrl(db.getUrl());
    config.setUsername(db.getUsername());
    config.setPassword(db.getPassword());
    config.setMinimumIdle(db.getConnection().getMinimumidle());
    config.setConnectionTimeout(db.getConnection().getConnectiontimeout());
    config.setIdleTimeout(db.getConnection().getIdletimeout());
    config.setMaximumPoolSize(db.getConnection().getMaxpoolsize());
    config.setMaxLifetime(db.getConnection().getMaxlifetime());
    config.setAutoCommit(db.getConnection().isAutocommit());

    return
            new
                    HikariDataSource(config);
  }

}