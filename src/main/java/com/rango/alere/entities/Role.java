package com.rango.alere.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_ALERE_ROLES")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_role", length = 40, nullable = false, unique = true)
    private String descricao;

    @Column(name = "is_deafult")
    private boolean isDefault;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;


    public Role(Long id, String descricao, boolean isDefault) {
        this.id = id;
        this.descricao = descricao;
        this.isDefault = isDefault;
    }

    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
