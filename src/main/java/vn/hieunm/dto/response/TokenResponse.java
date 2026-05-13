package vn.hieunm.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class TokenResponse implements Serializable {

    private String accesToken;

    private String refreshToken;

    private Long userId;

    // more over
}
