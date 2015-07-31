package com.verizon.cab.management.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.verizon.cab.management.config.data.LocalJpaRepositoryConfig;
import com.verizon.cab.management.repositories.AlbumRepository;

@Configuration
@ComponentScan(basePackageClasses = {AlbumRepository.class, LocalJpaRepositoryConfig.class})
public class RepositoryConfig {
}

