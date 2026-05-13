package vn.hieunm.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import vn.hieunm.entity.Role;
import vn.hieunm.repository.RoleRepository;

import java.util.List;

@Service
public record RoleService(RoleRepository roleRepository) {

    @PostConstruct
    public List<Role> findAll(){
        List<Role> roles = roleRepository.getallByUserId(2l);

        return roles;
    }
}
