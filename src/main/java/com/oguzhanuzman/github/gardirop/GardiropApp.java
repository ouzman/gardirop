package com.oguzhanuzman.github.gardirop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan(basePackageClasses = {GardiropApp.class, Jsr310JpaConverters.class})
@EnableJpaAuditing
public class GardiropApp {
    public static void main(String[] args) {
        SpringApplication.run(GardiropApp.class, args);
    }
}
