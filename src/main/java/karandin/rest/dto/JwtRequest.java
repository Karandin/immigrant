package karandin.rest.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String Username;
    private String password;

}
