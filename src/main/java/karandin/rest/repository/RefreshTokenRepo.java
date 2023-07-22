package karandin.rest.repository;

import karandin.rest.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends CrudRepository<RefreshTokenEntity,Integer> {

    Optional<RefreshTokenEntity> findByToken(String token);
}
