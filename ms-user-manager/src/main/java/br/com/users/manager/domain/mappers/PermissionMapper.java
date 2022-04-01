package br.com.users.manager.domain.mappers;

import br.com.users.manager.domain.dtos.PermissionDTO;
import br.com.users.manager.domain.dtos.ProfileDTO;
import br.com.users.manager.domain.entity.Permission;
import br.com.users.manager.domain.entity.Profile;
import org.mapstruct.*;

import java.util.List;

@Mapper( componentModel = "spring")
public abstract class PermissionMapper {

    @Named("fromDto")
    @Mappings({
            @Mapping(source = "permissionDTO.name", target = "name"),
    })
    public abstract Permission fromDto(PermissionDTO permissionDTO);

    @Named("toDto")
    @Mappings({
            @Mapping(source = "permission.name", target = "name"),
    })
    public abstract PermissionDTO toDto(Permission permission);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<PermissionDTO> mapToDto(List<Permission> children);

    @IterableMapping(qualifiedByName = "fromDto")
    public abstract List<Permission> mapFromDTO(List<PermissionDTO> children);
}
