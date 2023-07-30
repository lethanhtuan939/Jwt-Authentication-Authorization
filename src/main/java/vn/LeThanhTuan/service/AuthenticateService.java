package vn.LeThanhTuan.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.LeThanhTuan.auth.AuthenticationResponse;
import vn.LeThanhTuan.auth.RegisterRequest;
import vn.LeThanhTuan.entity.User;

import java.io.IOException;
import java.util.Optional;

public interface AuthenticateService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationResponse.AuthenticationRequest request);

    Optional<User> findByEmail(String email);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
