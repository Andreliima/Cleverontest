package ee.liima.cleverontest.controller;

import ee.liima.cleverontest.model.Permission;
import ee.liima.cleverontest.service.PermissionsService;
import ee.liima.cleverontest.service.dto.CreatePermissionDTO;
import ee.liima.cleverontest.service.dto.PermissionDTO;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("api/permissions")
public class PermissionsController {

    PermissionsService ps;

    @GetMapping("")
    public List<Permission> getAllPermissions(){
        return ps.getAllPermissions();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission addNewPermission(@Valid @RequestBody CreatePermissionDTO newPermission){
        return ps.addNewPermission(newPermission);
    }

    @GetMapping("{id}")
    public Permission getPermission(@PathVariable Long id){
        return ps.getPermission(id).orElseThrow(() -> new EntityNotFoundException("Requested Permission not found!"));
    }

    @PostMapping("{id}")
    public Permission addNewSubPermission(@PathVariable Long id, @Valid @RequestBody CreatePermissionDTO updatedPermission){
        return ps.addNewSubPermission(id, updatedPermission);
    }

    @PutMapping("{id}")
    public Permission updatePermission(@PathVariable Long id, @Valid @RequestBody PermissionDTO updatedPermission){
        return ps.updatePermission(id, updatedPermission);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Long id){
        ps.deletePermission(id);
        return ResponseEntity.ok("Permission deleted.");
    }

    @PutMapping("{id}/parent")
    public ResponseEntity<?> updateParent(@PathVariable(name = "id") Long permission, @RequestBody Long parent){
        ps.updateParent(permission, parent);
        return ResponseEntity.ok("Parent updated.");
    }

}
