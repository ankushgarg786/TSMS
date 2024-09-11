package com.tsms.management.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.tsms.management.entity.Role;
import com.tsms.management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) throws Exception {
        Optional<Role> existingRole=roleRepository.findByRoleName(role.getRoleName());
        if(existingRole.isPresent()){
            throw new IllegalArgumentException("Role with name '" + role.getRoleName() + "' already exists.");
        }
        else{
            return roleRepository.save(role);

        }
    }

    public Optional<Role> getRoleByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }
    public Optional<String> getIdByRoleName(String roleName){
        Optional<Role> role=roleRepository.findByRoleName(roleName);
        System.out.println(role);
        return role.map(Role::getId);
    }
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Optional<Role> updateRoleByRoleName(String roleName,Role updatedRole){
        Optional<Role> existingRole = roleRepository.findByRoleName(roleName);
        if (existingRole.isPresent()) {

            if (!existingRole.get().getId().equals(updatedRole.getId()) &&
                    roleRepository.findByRoleName(updatedRole.getRoleName()).isPresent()) {
                throw new IllegalArgumentException("Role with name '" + updatedRole.getRoleName() + "' already exists.");
            }
            existingRole.get().setRoleName(updatedRole.getRoleName());
            return Optional.of(roleRepository.save(existingRole.get()));
        }
        return Optional.empty();
    }
    public boolean deleteRoleByRoleName(String roleName){
        Optional<Role> existingRole=roleRepository.findByRoleName(roleName);
        if(existingRole.isPresent()){
            roleRepository.delete(existingRole.get());
            return true;
        }
        return false;

    }
}
