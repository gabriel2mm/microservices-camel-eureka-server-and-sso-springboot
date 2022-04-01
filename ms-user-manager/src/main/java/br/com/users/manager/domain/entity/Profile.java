package br.com.users.manager.domain.entity;

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
@Entity
@Table(name="tb_profiles")
public class Profile implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 30, nullable = false, unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_permission_profile",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private Set<Permission> permissions = new HashSet<Permission>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>();

    @CreationTimestamp
    @Column(name="data_criacao")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="data_de_atualizacao")
    private LocalDateTime updatedAt;
}
