package br.com.authentication.oauth.domain.mappers;

import br.com.authentication.oauth.domain.dtos.ProfileDTO;
import br.com.authentication.oauth.domain.entity.Profile;
import org.mapstruct.*;

import java.util.List;

@Mapper( componentModel = "spring")
public abstract class ProfileMapper {
    @Named("fromDto")
    @Mappings({
            @Mapping(source = "profileDTO.name", target = "name"),
            @Mapping(source = "profileDTO.permissions", target = "permissions"),
            @Mapping(source = "profileDTO.createdAt", target = "createdAt"),
            @Mapping(source = "profileDTO.updatedAt", target = "updatedAt"),
    })
    public abstract Profile fromDto(ProfileDTO profileDTO);

    @Named("toDto")
    @Mappings({
            @Mapping(source = "profile.name", target = "name"),
            @Mapping(source = "profile.permissions", target = "permissions"),
            @Mapping(source = "profile.createdAt", target = "createdAt"),
            @Mapping(source = "profile.updatedAt", target = "updatedAt"),
    })
    public abstract ProfileDTO toDto(Profile profile);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<ProfileDTO> mapToDto(List<Profile> children);

    @IterableMapping(qualifiedByName = "fromDto")
    public abstract List<Profile> mapFromDTO(List<ProfileDTO> children);
}
