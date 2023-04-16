package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.Role;
import com.digitalbooking.Back.response.ApiResponseHandler;
import com.digitalbooking.Back.services.serviceInterface.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Roles")
/*@CrossOrigin(origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole (@RequestBody Role role){
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRoleById(@PathVariable Integer id){
        Optional<Role> searchedRole = roleService.searchRoleById(id);
        if (searchedRole.isPresent()){
            return ApiResponseHandler.generateResponse("Role data retrieved successfully", HttpStatus.OK, searchedRole.get());
        }else {
            return ApiResponseHandler.generateResponseError("Role "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public  ResponseEntity<List<Role>> listRole(){
        return ResponseEntity.ok(roleService.listRoles());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRole(@RequestBody Role role){
        /*Optional<Role> searchedRole = roleService.searchRoleById(role.getId());*/
        ResponseEntity<String> response;
        if (roleService.searchRoleById(role.getId()).isPresent()){
            roleService.updateRole(role);
            response = ResponseEntity.ok("update role");
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Role with ID "+role.getId()+" is not found");
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
        return ResponseEntity.ok("The Role with id "+ id +" has been removed");
    }
}
