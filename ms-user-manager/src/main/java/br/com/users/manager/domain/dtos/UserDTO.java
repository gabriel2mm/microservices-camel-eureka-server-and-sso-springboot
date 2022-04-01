package br.com.users.manager.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String email;

    private String password;

    @JsonProperty("profile")
    private ProfileDTO profileDTO;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
