package br.com.users.manager.service;

import br.com.users.manager.domain.dtos.ProfileDTO;
import br.com.users.manager.domain.entity.Profile;
import br.com.users.manager.domain.mappers.ProfileMapper;
import br.com.users.manager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;

    private final ProfileRepository profileRepository;

    public ProfileDTO findById(Long id){
        return profileMapper.toDto(profileRepository.getById(id));
    }

    public List<ProfileDTO> findAll(){
        return profileMapper.mapToDto(profileRepository.findAll());
    }

    public void deleteProfile(Long id){
        profileRepository.deleteById(id);
    }

    public void saveProfile(ProfileDTO ProfileDTO){
        Profile profile = profileMapper.fromDto(ProfileDTO);
        profileRepository.saveAndFlush(profile);
    }

    public ProfileDTO updateProfile(Long id, ProfileDTO ProfileDTO){
        Profile profile = profileMapper.fromDto(ProfileDTO);
        profile.setId(id);
        profileRepository.saveAndFlush(profile);
        return ProfileDTO;
    }
}
