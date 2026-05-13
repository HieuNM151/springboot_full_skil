package vn.hieunm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import vn.hieunm.ultil.Platform;

@Getter
public class SignInRequest {

    @NotBlank(message = "Tài khoản không được để trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotNull(message = "Platform không được để trống")
    private Platform platform;

    private String deviceToken;

    private String version;
}
