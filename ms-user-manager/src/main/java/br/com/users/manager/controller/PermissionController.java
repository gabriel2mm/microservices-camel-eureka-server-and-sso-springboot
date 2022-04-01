package br.com.users.manager.controller;

import br.com.users.manager.domain.dtos.PermissionDTO;
import br.com.users.manager.service.PermissionService;
import br.com.users.manager.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> permissionDTOList(){
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PermissionDTO>> permissionDTOList(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.findAll());
    }

    @PostMapping
    public ResponseEntity createPermission(@RequestBody @Validated PermissionDTO permissionDTO){
        permissionService.savePermission(permissionDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@RequestParam("id") Long id, @RequestBody @Validated PermissionDTO permissionDTO){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(permissionService.updatePermission(id, permissionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePermission(@RequestParam("id") Long id){
        permissionService.deletePermission(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
