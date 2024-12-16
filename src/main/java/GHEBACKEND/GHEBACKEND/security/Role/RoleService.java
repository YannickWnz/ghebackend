package GHEBACKEND.GHEBACKEND.security.Role;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleById(Integer id){
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) return roleOptional.get();
        else throw new RuntimeException(String.format("Le role de code %s n'existe pas", id));
    }
}
