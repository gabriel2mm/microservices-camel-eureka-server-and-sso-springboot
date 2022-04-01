package br.com.users.manager.controller;

import br.com.users.manager.domain.dtos.LoginDTO;
import br.com.users.manager.domain.dtos.UserDTO;
import br.com.users.manager.domain.entity.User;
import br.com.users.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> userDTOList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserDTO>> userDTOList(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> createUser(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userService.signOn(loginDTO));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Validated UserDTO userDTO){
        userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestParam("id") Long id, @RequestBody @Validated UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
