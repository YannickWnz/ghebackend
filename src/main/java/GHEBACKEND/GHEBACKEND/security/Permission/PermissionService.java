package GHEBACKEND.GHEBACKEND.security.Permission;

import java.util.List;

import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.security.Role.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    
    public List<Permission> getPermissions(Role role){
        return permissionRepository.findByRole(role);
    }
}
