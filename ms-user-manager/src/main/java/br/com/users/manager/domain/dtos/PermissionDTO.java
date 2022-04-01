package br.com.users.manager.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PermissionDTO {

    @NotNull
    @NotBlank
    private String name;
}
