package br.com.users.manager.domain.dtos;

import br.com.users.manager.domain.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileDTO {

    @NotNull
    @NotBlank
    private String name;

    private Set<PermissionDTO> permissions = new HashSet<PermissionDTO>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
