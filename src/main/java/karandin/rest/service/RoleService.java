package karandin.rest.service;

import karandin.rest.entity.RoleEntity;
import karandin.rest.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleEntity getUserRole() {
        Optional<RoleEntity> roleOptional = roleRepo.findByName("ROLE_USER");
        return roleOptional.orElseThrow(() -> new NoSuchElementException("No value present"));

    }
}
