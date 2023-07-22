package karandin.rest.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import karandin.rest.entity.RefreshTokenEntity;
import karandin.rest.repository.RefreshTokenRepo;
import karandin.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class JwtRefreshTokenUtil {
    @Value("${jwt.refresh.secret}")
    private String refreshSecret;
    @Value("${jwt.refresh.lifetime}")
    private Duration refreshLifetime;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private UserRepo userRepo;

    public RefreshTokenEntity createRefreshToken(String username) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .userEntity(userRepo.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))//10
                .build();
        return refreshTokenRepo.save(refreshToken);
    }

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }


    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
