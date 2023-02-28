package za.flatrock.assessment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.flatrock.assessment.demo.models.RoleENUM;
import za.flatrock.assessment.demo.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(RoleENUM role);
}
