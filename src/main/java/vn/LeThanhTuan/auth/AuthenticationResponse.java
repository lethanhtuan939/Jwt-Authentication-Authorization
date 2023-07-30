package vn.LeThanhTuan.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AuthenticationRequest {
        private String email;
        String password;
    }
}
