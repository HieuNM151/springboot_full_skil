package vn.hieunm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;

import vn.hieunm.dto.request.UserRequestDTO;
import vn.hieunm.dto.request.UserRequestSearchDTO;
import vn.hieunm.dto.response.UserDetailResponse;
import vn.hieunm.entity.Address;
import vn.hieunm.entity.User;
import vn.hieunm.exception.ResourceNotFoundExeption;
import vn.hieunm.repository.UserRepository;
import vn.hieunm.service.UserService;
import vn.hieunm.ultil.ObjectMapperUtils;
import vn.hieunm.ultil.UserStatus;
import vn.hieunm.ultil.UserType;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapperUtils mapper;

    @Override
    public long saveUser(UserRequestDTO request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .phone(request.getPhone())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .status(request.getStatus())
                .type(UserType.valueOf(request.getType()))
                .build();

        if (request.getAddresses() != null) {
            request.getAddresses().forEach(a ->
                    user.addAddress(Address.builder()
                            .apartmentNumber(a.getApartmentNumber())
                            .floor(a.getFloor())
                            .building(a.getBuilding())
                            .streetNumber(a.getStreetNumber())
                            .street(a.getStreet())
                            .city(a.getCity())
                            .country(a.getCountry())
                            .addressType(a.getAddressType())
                            .build())
            );
        }

        userRepository.save(user);
        log.info("User has added successfully, userId={}", user.getId());
        return user.getId();
    }

    /* ================= UPDATE ================= */
    @Override
    public void updateUser(long userId, UserRequestDTO request) {

        User user = getUserById(userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        user.setType(UserType.valueOf(request.getType()));

        // update address: clear + add
        user.getAddresses().clear();
        if (request.getAddresses() != null) {
            request.getAddresses().forEach(a ->
                    user.addAddress(Address.builder()
                            .apartmentNumber(a.getApartmentNumber())
                            .floor(a.getFloor())
                            .building(a.getBuilding())
                            .streetNumber(a.getStreetNumber())
                            .street(a.getStreet())
                            .city(a.getCity())
                            .country(a.getCountry())
                            .addressType(a.getAddressType())
                            .build())
            );
        }

        userRepository.save(user);
        log.info("User updated successfully, userId={}", userId);
    }

    /* ================= CHANGE STATUS ================= */
    @Override
    public void changeStatus(long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);

        log.info("User status changed, userId={}, status={}", userId, status);
    }

    /* ================= DELETE ================= */
    @Override
    public void deleteUser(long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);

        log.info("User deleted successfully, userId={}", userId);
    }

    /* ================= GET ONE ================= */
    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return mapper.map(user, UserDetailResponse.class);
    }

    /* ================= GET ALL ================= */
    @Override
    public List<UserDetailResponse> getAllUsers(UserRequestSearchDTO request) {

        Pageable pageable = PageRequest.of(
                request.getPageNo(),
                request.getPageSize()
        );

        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 🔍 search keyword
            if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
                String like = "%" + request.getKeyword().toLowerCase() + "%";
                predicates.add(
                        cb.or(
                                cb.like(cb.lower(root.get("firstName")), like),
                                cb.like(cb.lower(root.get("lastName")), like),
                                cb.like(cb.lower(root.get("email")), like),
                                cb.like(cb.lower(root.get("phone")), like)
                        )
                );
            }

            // 🔖 filter status
            if (request.getStatus() != null) {
                predicates.add(
                        cb.equal(root.get("status"), request.getStatus())
                );
            }

            // 🔖 filter type
            if (request.getType() != null) {
                predicates.add(
                        cb.equal(root.get("type"), request.getType())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<User> page = userRepository.findAll(spec, pageable);

        return mapper.mapAll(page.getContent(), UserDetailResponse.class);
    }



    /* ================= PRIVATE ================= */
    private User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundExeption(
                                "User not found with id = " + userId
                        )
                );
    }
}

