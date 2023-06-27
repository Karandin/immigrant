package karandin.rest.repository;

import karandin.rest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail (String email);
}
