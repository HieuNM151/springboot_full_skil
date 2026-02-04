package vn.hieunm.dto.request;

import lombok.Data;
import vn.hieunm.ultil.UserStatus;
import vn.hieunm.ultil.UserType;
@Data
public class UserRequestSearchDTO {
    private int pageNo;
    private int pageSize;
    private String keyword;
    private UserStatus status;
    private UserType type;
}
