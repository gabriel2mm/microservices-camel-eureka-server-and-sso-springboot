package br.com.authentication.oauth.feign;

import br.com.authentication.oauth.domain.dtos.LoginDTO;
import br.com.authentication.oauth.domain.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name="ms-user-manager", path="/api/v1/users")
public interface UserFeignClient {

    @PostMapping("/login")
    ResponseEntity<UserDTO> signOn(@RequestBody LoginDTO loginDTO);
}
