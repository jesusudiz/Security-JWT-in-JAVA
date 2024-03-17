package com.security.securityJWT.controller;

import com.security.securityJWT.controller.request.CreateUserDTO;
import com.security.securityJWT.models.ERole;
import com.security.securityJWT.models.RoleEntity;
import com.security.securityJWT.models.UserEntity;
import com.security.securityJWT.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/hello")
    public String hello() {
        return "Hello World not secured";
    }
    @GetMapping("/helloSecured")
    public String helloSecured() {
        return "Hello World secured";
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        Set<RoleEntity> roles= createUserDTO.getRoles().stream()
        .map(role->RoleEntity.builder()
                .name(ERole.valueOf(role))
                .build())
        .collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam String id){
    userRepository.deleteById(Long.parseLong(id));
    return "se ha eliminado el usuario con el id".concat(id);
    }
}
