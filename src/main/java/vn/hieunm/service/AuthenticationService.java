package vn.hieunm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.hieunm.dto.request.SignInRequest;
import vn.hieunm.dto.response.TokenResponse;
import vn.hieunm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public TokenResponse authenticate(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Tài khoản hoặc mật khẩu sai!"));

        String accessToken = "DUMMY-TOKEN";

        return TokenResponse.builder()
                .accesToken(accessToken)
                .refreshToken("refresh_token")
                .userId(user.getId())
                .build();
    }
}
