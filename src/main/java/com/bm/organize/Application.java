package com.bm.organize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public OrganizeFile organizeFile() {
    return new OrganizeFileSimpleImpl();
  }

  @Bean
  CommandLineRunner commandLineRunner(OrganizeFile organizeFile,
      @Value("${input.path}") final String inputPath) {
    log.info("=========================={}==========================", inputPath);
    return args -> organizeFile.organize(inputPath);
  }

}
