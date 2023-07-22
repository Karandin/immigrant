package karandin.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String token;
        private Instant expiryDate;

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private UserEntity userEntity;


}
