package br.com.users.manager.service;

import br.com.users.manager.domain.dtos.LoginDTO;
import br.com.users.manager.domain.dtos.UserDTO;
import br.com.users.manager.domain.entity.User;
import br.com.users.manager.domain.exceptions.ObjectNotFound;
import br.com.users.manager.domain.mappers.UserMapper;
import br.com.users.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserDTO signOn(@RequestBody LoginDTO loginDTO){
       User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ObjectNotFound("Usuário não encontrado."));
       return userMapper.toDto(user);
    }

    public UserDTO findById(Long id){
        return userMapper.toDto(userRepository.getById(id));
    }

    public List<UserDTO> findAll(){
        return userMapper.mapToDto(userRepository.findAll());
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void saveUser(UserDTO userDTO){
        User user = userMapper.fromDto(userDTO);
        userRepository.saveAndFlush(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO){
        User user = userMapper.fromDto(userDTO);
        user.setId(id);
        userRepository.saveAndFlush(user);
        return userDTO;
    }
}
