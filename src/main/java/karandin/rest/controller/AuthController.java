package karandin.rest.controller;


import karandin.rest.dto.JwtRequest;
import karandin.rest.dto.JwtResponse;
import karandin.rest.dto.RefreshTokenRequest;
import karandin.rest.dto.RegistrationUserDto;
import karandin.rest.entity.RefreshTokenEntity;
import karandin.rest.service.AuthService;
import karandin.rest.service.UserService;
import karandin.rest.util.JwtRefreshTokenUtil;
import karandin.rest.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    private JwtTokenUtil jwtTokenUtil;
    private JwtRefreshTokenUtil jwtRefreshTokenUtil;

    private UserService userService;

    public AuthController(AuthService authService, JwtRefreshTokenUtil jwtRefreshTokenUtil, JwtTokenUtil jwtTokenUtil,
                          UserService userService) {
        this.authService = authService;
        this.jwtRefreshTokenUtil = jwtRefreshTokenUtil;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        RefreshTokenEntity refreshToken = jwtRefreshTokenUtil.createRefreshToken(authRequest.getUsername());
        //return authService.createAuthToken(authRequest);
        return JwtResponse.builder()
                .accessToken(jwtTokenUtil.generateToken(userService.loadUserByUsername(authRequest.getUsername())))
                .token(refreshToken.getToken()).build();

    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return jwtRefreshTokenUtil.findByToken(refreshTokenRequest.getToken())
                .map(jwtRefreshTokenUtil::verifyExpiration)
                .map(RefreshTokenEntity::getUserEntity)
                .map(userInfo -> {
                    String accessToken = jwtTokenUtil.generateToken(userService.loadUserByUsername(userInfo.getUsername()));
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}
