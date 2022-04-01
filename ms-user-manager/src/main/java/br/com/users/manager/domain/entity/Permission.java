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
@Table(name="tb_permissions")
public class Permission implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 40, nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(name="data_criacao")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="data_de_atualizacao")
    private LocalDateTime updatedAt;
}
