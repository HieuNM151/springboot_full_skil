package vn.hieunm.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;
import vn.hieunm.dto.request.UserRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/")
    public String addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        return "User created successfully!";
    }

    @PutMapping("/{userId}")
    public String updateUser(
            @PathVariable int userId,
            @Valid @RequestBody UserRequestDTO userDTO
    ) {
        System.out.println("Updating user with ID: " + userId);
        return "User updated successfully!";
    }

    @PatchMapping("/{userId}")
    public String changeStatus(
            @PathVariable int userId,
            @Min(1) @RequestParam(required = false) int status
    ) {
        System.out.println("Request change user status, userId=" + userId);
        return "User status changed";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@Min (1) @PathVariable int userId) {
        System.out.println("Request delete userId=" + userId);
        return "User deleted";
    }

    @GetMapping("/{userId}")
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
