package vn.hieunm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
public class Role extends AbtractEntity<Integer>{
    private String name;
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<RoleHasPermission> permissions;

    @OneToMany(mappedBy = "role")
    private Set<UserHasRole> roles = new HashSet<>();
}
