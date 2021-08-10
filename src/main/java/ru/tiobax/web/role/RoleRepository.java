package ru.tiobax.web.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tiobax.web.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRole(String nameRole);
}
