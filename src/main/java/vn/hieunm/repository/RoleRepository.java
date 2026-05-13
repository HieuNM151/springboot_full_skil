package vn.hieunm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.hieunm.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r " +
            "inner join UserHasRole ur on r.id = ur.user.id " +
            "WHERE ur.user.id = :userId")
    List<Role> getallByUserId(Long userId);
}
