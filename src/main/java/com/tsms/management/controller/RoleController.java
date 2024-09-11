package com.tsms.management.controller;

import com.tsms.management.entity.Role;
import com.tsms.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody  Role role) throws Exception {
        Role createdRole=roleService.createRole(role);
   return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
}
    @GetMapping("/{roleName}")
    public ResponseEntity<?> getRoleByRoleName(@PathVariable String roleName) {
        Optional<Role> role = roleService.getRoleByRoleName(roleName);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get()); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found"); // HTTP 404 Not Found
        }
    }

    // Get all Roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles); // HTTP 200 OK
    }
    @GetMapping("/name/{roleName}")
    public ResponseEntity<?> getIdByRoleName(@PathVariable String roleName){
        Optional<String> roleId=roleService.getIdByRoleName(roleName);
        if(roleId.isPresent()){
            return ResponseEntity.ok(roleId.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");  // HTTP 404 Not Found
        }
    }

    // Update a Role by roleName
    @PutMapping("/{roleName}")
    public ResponseEntity<?> updateRole(@PathVariable String roleName, @RequestBody Role updatedRole) {
        Optional<Role> updated = roleService.updateRoleByRoleName(roleName, updatedRole);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get()); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found for update"); // HTTP 404 Not Found
        }
    }

    // Delete a Role by roleName
    @DeleteMapping("/{roleName}")
    public ResponseEntity<String> deleteRoleByRoleName(@PathVariable String roleName) {
        boolean deleted = roleService.deleteRoleByRoleName(roleName);
        if (deleted) {
            return ResponseEntity.ok("Role deleted successfully"); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found for deletion"); // HTTP 404 Not Found
        }
    }

}