package com.backend.test.bookstore.configuration.beans;

import com.backend.test.bookstore.domain.converter.TestProjectModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(Set<TestProjectModelMapperConverter<?, ?>> testProjectModelMapperConverter) {
        ModelMapper modelMapper = new ModelMapper();
        testProjectModelMapperConverter.forEach(modelMapper::addConverter);
        return modelMapper;
    }
}