package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {


    Role createRole (Role role);
    Optional<Role> searchRoleById (Integer id);
    List<Role> listRoles();
    Role updateRole(Role role);
    void deleteRole(Integer id);
}
