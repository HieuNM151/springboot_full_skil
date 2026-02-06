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
@Table(name = "tbl_permission")
public class Permission extends AbtractEntity<Integer>{
    private String name;
    private String description;

    @OneToMany(mappedBy = "permission")
    private Set<RoleHasPermission> roleHasPermissions = new HashSet<>();
}
