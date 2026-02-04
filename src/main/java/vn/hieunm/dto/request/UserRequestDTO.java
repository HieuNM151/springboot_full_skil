package vn.hieunm.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import vn.hieunm.ultil.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static vn.hieunm.ultil.Gender.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "Phải nhập tên vào cho bố")
    private String firstName;

    @NotBlank(message = "Phải nhập họ và vào cho bố")
    private String lastName;

    @Email(message = "Email không đúng định dạng")
    private String email;

    @PhoneNumber
    private String phone;

    @NotNull(message = "Phải nhập ngày sinh vào cho bố")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE")
    private UserStatus status;

    @GenderSubset(anyOf = {MALE, FEMALE, OTHER})
    private Gender gender;

    @NotNull(message = "type must be not null")
    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    @NotNull(message = "Phải nhập tên đăng nhập vào cho bố")
    private String username;

    @NotNull(message = "Phải nhập mật khẩu vào cho bố")
    private String password;

    private List<String> permissions;

    /* ===== ADDRESS ===== */
    @NotEmpty(message = "User phải có ít nhất 1 địa chỉ")
    @Valid
    private List<AddressDTO> addresses;
}

