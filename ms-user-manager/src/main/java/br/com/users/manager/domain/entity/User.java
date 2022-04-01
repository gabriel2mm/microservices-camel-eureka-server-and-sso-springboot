package br.com.users.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name="tb_usuários")
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 60, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 150, nullable = false)
    private String password;

    @Column(name = "ativo", length = 50, nullable = false)
    private boolean active;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Profile.class)
    private Profile profile;

    @CreationTimestamp
    @Column(name="data_criacao")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="data_de_atualizacao")
    private LocalDateTime updatedAt;
}
