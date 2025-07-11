package com.ecommerce.user.service;



import com.ecommerce.user.dtos.UserRequest;
import com.ecommerce.user.dtos.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    void createUser(UserRequest userRequest);

    Boolean updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);

}
