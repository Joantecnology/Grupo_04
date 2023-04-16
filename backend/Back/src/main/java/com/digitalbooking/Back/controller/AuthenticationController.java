package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.model.dto.AuthenticationRequestDto;
import com.digitalbooking.Back.model.dto.AuthenticationResponseDto;
import com.digitalbooking.Back.exception.AuthenticationException;

import com.digitalbooking.Back.security.jwt.JwtUtil;
import com.digitalbooking.Back.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Api(tags="Authentication")
@CrossOrigin("*")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new AuthenticationException("Incorrect or invalid credentials");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        final int id = userServiceImpl.userId(authenticationRequest.getEmail());
        final String name = userServiceImpl.userName(authenticationRequest.getEmail());
        final String lastName = userServiceImpl.userLastname(authenticationRequest.getEmail());
        final String email = userServiceImpl.userEmail(authenticationRequest.getEmail());
        final String city = userServiceImpl.userCity(authenticationRequest.getEmail());
        final String role = userServiceImpl.userRole(authenticationRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationResponseDto((jwt), id, name, lastName, email, city, role));
    }
}
