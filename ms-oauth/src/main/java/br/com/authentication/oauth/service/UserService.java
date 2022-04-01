package br.com.authentication.oauth.service;

import br.com.authentication.oauth.domain.dtos.LoginDTO;
import br.com.authentication.oauth.domain.entity.User;
import br.com.authentication.oauth.domain.mappers.UserMapper;
import br.com.authentication.oauth.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDTO loginDTO = new LoginDTO(username, "");
        User user = userMapper.fromDto(userFeignClient.signOn(loginDTO).getBody());
        return user;
    }
}
