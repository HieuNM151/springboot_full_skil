package vn.hieunm.dto.response;

import lombok.*;
import vn.hieunm.dto.request.AddressDTO;
import vn.hieunm.ultil.Gender;
import vn.hieunm.ultil.UserStatus;
import vn.hieunm.ultil.UserType;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailResponse implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;
    private UserStatus status;
    private UserType type;

    private List<AddressDTO> addresses;
}

