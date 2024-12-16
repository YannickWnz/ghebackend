package GHEBACKEND.GHEBACKEND.security.Permission;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.security.Role.Role;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer>{

    List<Permission> findByRole(Role role);
}
