package br.com.users.manager.domain.mappers;

import br.com.users.manager.domain.dtos.UserDTO;
import br.com.users.manager.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract  class UserMapper {

    @Named("fromDto")
    @Mappings({
            @Mapping(source = "userDTO.name", target = "name"),
            @Mapping(source = "userDTO.email", target = "email"),
            @Mapping(source = "userDTO.password", target = "password"),
            @Mapping(source = "userDTO.profileDTO.name", target = "profile.name"),
            @Mapping(source = "userDTO.profileDTO.permissions", target = "profile.permissions"),
            @Mapping(source = "userDTO.profileDTO.createdAt", target = "profile.createdAt"),
            @Mapping(source = "userDTO.profileDTO.updatedAt", target = "profile.updatedAt"),
            @Mapping(source = "userDTO.createdAt", target = "createdAt"),
            @Mapping(source = "userDTO.updatedAt", target = "updatedAt"),
    })
    public abstract User fromDto(UserDTO userDTO);

    @Named("toDto")
    @Mappings({
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.profile.name", target = "profileDTO.name"),
            @Mapping(source = "user.profile.permissions", target = "profileDTO.permissions"),
            @Mapping(source = "user.profile.createdAt", target = "profileDTO.createdAt"),
            @Mapping(source = "user.profile.updatedAt", target = "profileDTO.updatedAt"),
            @Mapping(source = "user.createdAt", target = "createdAt"),
            @Mapping(source = "user.updatedAt", target = "updatedAt"),
    })
    public abstract UserDTO toDto(User user);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<UserDTO> mapToDto(List<User> children);

    @IterableMapping(qualifiedByName = "fromDto")
    public abstract List<User> mapFromDTO(List<UserDTO> children);
}
