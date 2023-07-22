package karandin.rest.repository;

import karandin.rest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername (String email);
}
