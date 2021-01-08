package ee.liima.cleverontest.service;

import ee.liima.cleverontest.model.Permission;
import ee.liima.cleverontest.repository.PermissionsRepository;
import ee.liima.cleverontest.service.dto.CreatePermissionDTO;
import ee.liima.cleverontest.service.dto.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionsService {

    private PermissionsRepository pr;

    public List<Permission> getAllPermissions() {
        return pr.findAll();
    }

    public Permission addNewPermission(CreatePermissionDTO newPermission) {
        var p = Permission.builder()
                .name(newPermission.getName())
                .build();
        return pr.save(p);
    }

    public Optional<Permission> getPermission(Long id) {
        return pr.findById(id);
    }

    public Permission updatePermission(Long id, PermissionDTO permissionUpdate) {
        var p = pr.getOne(id);
        p.setName(permissionUpdate.getName());
        if (permissionUpdate.getParentId() != null) {
            p.setParent(pr.getOne(permissionUpdate.getParentId()));
        }else{
            p.setParent(null);
        }
        return pr.save(p);
    }

    public void updateParent(Long permission, Long parent) {
        var p = pr.getOne(permission);
        p.setParent(pr.getOne(parent));
        pr.save(p);
    }

    public void deletePermission(Long id) {
        pr.deleteById(id);
    }

    public Permission addNewSubPermission(Long id, @Valid CreatePermissionDTO newPermission) {
        var p = Permission.builder()
                .name(newPermission.getName())
                .parent(pr.getOne(id))
                .build();
        return pr.save(p);
    }
}
