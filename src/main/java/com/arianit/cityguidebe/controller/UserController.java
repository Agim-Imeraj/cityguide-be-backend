package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.UserRequest;
import com.arianit.cityguidebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(userService.update(id,fields));
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User marked as deleted");
    }

    @GetMapping("/favorites")
    public List<GastronomeDto> getUserFavoriteGastronomies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserFavoriteGastronomies(username);
    }
}
