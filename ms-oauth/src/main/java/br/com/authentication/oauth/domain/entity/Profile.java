package br.com.authentication.oauth.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Profile implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Set<Permission> permissions = new HashSet<Permission>();
    private Set<User> users = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
