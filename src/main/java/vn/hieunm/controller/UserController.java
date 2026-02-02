package vn.hieunm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hieunm.configuration.Translator;
import vn.hieunm.dto.request.UserRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
public class UserController {

    @Operation(summary = "Add new user", description = "API thêm mới người dùng")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        String message = Translator.toLocale("user.add.successfully");

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateUser(
            @PathVariable int userId,
            @Valid @RequestBody UserRequestDTO userDTO
    ) {
        System.out.println("Updating user with ID: " + userId);
        return "User updated successfully!";
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String changeStatus(
            @PathVariable int userId,
            @Min(1) @RequestParam(required = false) int status
    ) {
        System.out.println("Request change user status, userId=" + userId);
        return "User status changed";
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@Min (1) @PathVariable int userId) {
        System.out.println("Request delete userId=" + userId);
        return "User deleted";
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserRequestDTO getUser(@PathVariable int userId) {
        System.out.println("Request get user detail by userId: " + userId);

        return UserRequestDTO.builder()
                .firstName("Tay")
                .lastName("Java")
                .phone("phone")
                .email("email")
                .build();
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRequestDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        System.out.println("Request get user list");

        return List.of(
                UserRequestDTO.builder()
                        .firstName("Tay")
                        .lastName("Java")
                        .phone("phone")
                        .email("email")
                        .build(),
                UserRequestDTO.builder()
                        .firstName("Tay")
                        .lastName("Java")
                        .phone("phone")
                        .email("email")
                        .build()
        );
    }
}
