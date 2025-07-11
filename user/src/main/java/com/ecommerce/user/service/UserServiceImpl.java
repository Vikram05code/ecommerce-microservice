package com.ecommerce.user.service;

import com.app.ecom.dtos.AddressDTO;
import com.app.ecom.dtos.UserRequest;
import com.app.ecom.dtos.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToUserResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    @Override
    public void createUser(UserRequest userRequest) {
        User user = new User();
        mapToUserFromRequest(user, userRequest);
        userRepository.save(user);

    }

    @Override
    public Boolean updateUser(Long id, UserRequest userRequest) {
       return userRepository.findById(id).map(existingUser ->{
           mapToUserFromRequest(existingUser, userRequest);
           userRepository.save(existingUser);
           return  true;
       }).orElse(false);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    private void mapToUserFromRequest(User user, UserRequest userRequest){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddressDTO() != null){
           Address address = new Address();
           address.setCity(userRequest.getAddressDTO().getCity());
           address.setCountry(userRequest.getAddressDTO().getCountry());
           address.setStreet(userRequest.getAddressDTO().getStreet());
           address.setState(userRequest.getAddressDTO().getState());
           address.setZipCode(userRequest.getAddressDTO().getZipCode());
           user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setZipCode(user.getAddress().getZipCode());
            userResponse.setAddressDTO(addressDTO);
        }
        return userResponse;
    }

}
