package br.com.users.manager.service;

import br.com.users.manager.domain.dtos.PermissionDTO;
import br.com.users.manager.domain.entity.Permission;
import br.com.users.manager.domain.mappers.PermissionMapper;
import br.com.users.manager.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionMapper permissionMapper;

    private final PermissionRepository permissionRepository;

    public PermissionDTO findById(Long id){
        return permissionMapper.toDto(permissionRepository.getById(id));
    }

    public List<PermissionDTO> findAll(){
        return permissionMapper.mapToDto(permissionRepository.findAll());
    }

    public void deletePermission(Long id){
        permissionRepository.deleteById(id);
    }

    public void savePermission(PermissionDTO permissionDTO){
        Permission permission = permissionMapper.fromDto(permissionDTO);
        permissionRepository.saveAndFlush(permission);
    }

    public PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO){
        Permission permission = permissionMapper.fromDto(permissionDTO);
        permission.setId(id);
        permissionRepository.saveAndFlush(permission);
        return permissionDTO;
    }

}
