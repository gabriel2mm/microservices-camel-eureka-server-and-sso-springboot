package br.com.authentication.oauth.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

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
