package com.example.study_security.maptructs;

import com.example.study_security.data.dto.response.RolesDto;
import com.example.study_security.data.dto.response.UsersDto;
import com.example.study_security.data.entity.Users;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-11T20:19:47+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.11 (JetBrains s.r.o.)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public Users toEntity(UsersDto usersDto) {
        if ( usersDto == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        users.userId( usersDto.getUserId() );
        users.username( usersDto.getUsername() );
        users.password( usersDto.getPassword() );

        return users.build();
    }

    @Override
    public UsersDto toDto(Users users) {
        if ( users == null ) {
            return null;
        }

        Integer userId = null;
        String username = null;
        String password = null;

        userId = users.getUserId();
        username = users.getUsername();
        password = users.getPassword();

        Set<RolesDto> roles = null;

        UsersDto usersDto = new UsersDto( userId, username, password, roles );

        return usersDto;
    }
}
