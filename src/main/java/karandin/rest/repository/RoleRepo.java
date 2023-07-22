package karandin.rest.repository;

import karandin.rest.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String email);

}
