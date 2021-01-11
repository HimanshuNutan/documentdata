package com.ac.documentdata.configuration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@ConfigurationProperties(prefix = "app")
@ConstructorBinding
@Getter
@AllArgsConstructor
@Validated
public class ApplicationProperties {

  private DB db;

  @Getter
  @AllArgsConstructor
  @Validated
  public static class DB {

    private String url;
    private String username;
    private String password;
    private String driverclassname;

    private Connection connection;

    @Getter
    @AllArgsConstructor
    @Validated
    public static class Connection {

      private long connectiontimeout;

      private int minimumidle;

      private long idletimeout;

      private int maxpoolsize;

      private long maxlifetime;

      private boolean autocommit;
    }
  }
}
