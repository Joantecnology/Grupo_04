package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.exception.DuplicatedValueException;
import com.digitalbooking.Back.exception.RegisteredUserException;
import com.digitalbooking.Back.model.User;
import com.digitalbooking.Back.model.dto.UserDto;
import com.digitalbooking.Back.response.ApiResponseHandler;
import com.digitalbooking.Back.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Users")
/*@CrossOrigin(origins = "*")*/
/* agrego al crosing, la direccion del front*/
@CrossOrigin (origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) throws RegisteredUserException {
        return ApiResponseHandler.generateResponse("the information has been saved successfully", HttpStatus.OK, userService.createUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
        Optional<User> userSearch = userService.searchUserById(id);
        if(userSearch.isPresent()){
            return ApiResponseHandler.generateResponse("User data retrieved successfully", HttpStatus.OK, userSearch.get());
        } else {
            return ApiResponseHandler.generateResponseError("User "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.listUser());
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        if(userDto.getId() != null && userService.searchUserById(userDto.getId()).isPresent()){
            return ApiResponseHandler.generateResponse("User data update successfully", HttpStatus.OK, userService.updateUser(userDto));
        } else {
            return ApiResponseHandler.generateResponseError("User with ID: "+ userDto.getId() + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        if(userService.searchUserById(id).isPresent()){
            userService.deleteUser(id);
            return ApiResponseHandler.generateResponse(null, HttpStatus.NO_CONTENT, null);
        } else {
            return ApiResponseHandler.generateResponseError("User "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
