package vn.hieunm.service;

import vn.hieunm.dto.request.UserRequestDTO;
import vn.hieunm.dto.request.UserRequestSearchDTO;
import vn.hieunm.dto.response.UserDetailResponse;
import vn.hieunm.ultil.UserStatus;
import vn.hieunm.ultil.UserType;

import java.util.List;

public interface UserService {
    long saveUser(UserRequestDTO request);

    void updateUser(long userId, UserRequestDTO request);

    void changeStatus(long userId, UserStatus status);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    List<UserDetailResponse> getAllUsers(UserRequestSearchDTO request);
}
