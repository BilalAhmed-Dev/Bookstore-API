package com.backend.test.bookstore.domain.converter;

import org.springframework.stereotype.Component;
import com.backend.test.bookstore.domain.entity.UserEntity;
import com.backend.test.bookstore.domain.model.UserInfo;
import org.modelmapper.spi.MappingContext;


@Component
public class UserEntityToUserInfoConverter implements TestProjectModelMapperConverter<UserEntity, UserInfo> {


    public UserEntityToUserInfoConverter() {

    }

    @Override
    public UserInfo convert(MappingContext<UserEntity, UserInfo> context) {
        UserEntity source = context.getSource();
        UserInfo destination = new UserInfo();

        destination.setId(source.getId());



        // Map fields from UserEntity to UserInfo
        destination.setUsername(source.getUsername());
        destination.setEmail(source.getEmail());
        destination.setAdmin(source.isAdmin());


        // Add any additional fields as needed
        // e.g., destination.setRole(source.getRole());

        return destination;
    }
}