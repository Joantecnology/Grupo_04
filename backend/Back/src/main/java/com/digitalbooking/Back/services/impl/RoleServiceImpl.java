package com.digitalbooking.Back.services.impl;

import com.digitalbooking.Back.model.Role;
import com.digitalbooking.Back.repository.RoleRepository;
import com.digitalbooking.Back.services.serviceInterface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
     RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> searchRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
