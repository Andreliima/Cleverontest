package ee.liima.cleverontest.repository;

import ee.liima.cleverontest.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepository extends JpaRepository<Permission, Long> {

}
