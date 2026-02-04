package vn.hieunm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hieunm.dto.request.UserRequestDTO;
import vn.hieunm.dto.request.UserRequestSearchDTO;
import vn.hieunm.dto.response.ResponseData;
import vn.hieunm.dto.response.UserDetailResponse;
import vn.hieunm.service.UserService;
import vn.hieunm.ultil.UserStatus;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /* ================= SEARCH + PAGING ================= */
    @PostMapping("/search")
    public ResponseEntity<ResponseData<List<UserDetailResponse>>> searchUsers(
            @RequestBody UserRequestSearchDTO request
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Get users successfully",
                        userService.getAllUsers(request)
                )
        );
    }

    /* ================= CREATE ================= */
    @PostMapping("/add")
    public ResponseEntity<ResponseData<Long>> addUser(
            @Valid @RequestBody UserRequestDTO request
    ) {
        long userId = userService.saveUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(
                        HttpStatus.CREATED.value(),
                        "Add user successfully",
                        userId
                ));
    }

    /* ================= UPDATE ================= */
    @PostMapping("/update/{userId}")
    public ResponseEntity<ResponseData<Void>> updateUser(
            @PathVariable long userId,
            @Valid @RequestBody UserRequestDTO request
    ) {
        userService.updateUser(userId, request);

        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Update user successfully",
                        null
                )
        );
    }

    /* ================= CHANGE STATUS ================= */
    @PostMapping("/change-status/{userId}")
    public ResponseEntity<ResponseData<Void>> changeStatus(
            @PathVariable long userId,
            @RequestParam UserStatus status
    ) {
        userService.changeStatus(userId, status);

        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Change status successfully",
                        null
                )
        );
    }

    /* ================= DELETE ================= */
    @PostMapping("/delete/{userId}")
    public ResponseEntity<ResponseData<Void>> deleteUser(
            @PathVariable long userId
    ) {
        userService.deleteUser(userId);

        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Delete user successfully",
                        null
                )
        );
    }

    /* ================= GET ONE ================= */
    @PostMapping("/detail/{userId}")
    public ResponseEntity<ResponseData<UserDetailResponse>> getUser(
            @PathVariable long userId
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Get user successfully",
                        userService.getUser(userId)
                )
        );
    }

}
