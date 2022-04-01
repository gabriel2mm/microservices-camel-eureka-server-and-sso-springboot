package br.com.users.manager.controller;

import br.com.users.manager.domain.dtos.ProfileDTO;
import br.com.users.manager.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> profileDTOList(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProfileDTO>> profileDTOList(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.findAll());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Validated ProfileDTO profileDTO){
        profileService.saveProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateUser(@RequestParam("id") Long id, @RequestBody @Validated ProfileDTO profileDTO){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileService.updateProfile(id, profileDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestParam("id") Long id){
        profileService.deleteProfile(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
