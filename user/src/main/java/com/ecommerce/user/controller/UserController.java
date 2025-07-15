package com.ecommerce.user.controller;

import com.ecommerce.user.dtos.UserRequest;
import com.ecommerce.user.dtos.UserResponse;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value="/getAll")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> usersList = userService.getAllUsers();
    return ResponseEntity.ok(usersList);
}

@GetMapping("/{id}")
public ResponseEntity<UserResponse> getUserById(@PathVariable String id){
    return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

}

@PostMapping(value="/add")
public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        return ResponseEntity.ok("User Created Successfully");
}

@PutMapping(value="/update/{id}")
public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest){
    return userService.updateUser(id, userRequest) ? ResponseEntity.ok("User Updated Successfully") : ResponseEntity.notFound().build();
}

}