package com.digitalbooking.Back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.digitalbooking.Back.exception.RegisteredUserException;
import com.digitalbooking.Back.model.Role;
import com.digitalbooking.Back.model.User;
import com.digitalbooking.Back.model.dto.UserDto;
import com.digitalbooking.Back.repository.RoleRepository;
import com.digitalbooking.Back.repository.UserRepository;
import com.digitalbooking.Back.services.serviceInterface.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper mapper;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //@Override saco los override de los metodos normales porque se esta implementando el user details
    public User createUser(UserDto userDto) throws RegisteredUserException {
        Optional<User> registeredUser = userRepository.getUserByEmail(userDto.getEmail());

        if (!registeredUser.isPresent()){
            throw new RegisteredUserException("email already registered, please register another");
        }
        Role roleUser = roleRepository.findById(userDto.getRole().getId()).get();
        userDto.setRole(roleUser);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapper.convertValue(userDto, User.class);

        return userRepository.save(user);
    }


    public Optional<User> searchUserById(Integer id) {
        return userRepository.findById(id);
    }


    public List<UserDto> listUser() {
        List<User> listUsers = userRepository.findAll();
        List<UserDto> listUsersDto = new ArrayList<>();

        for(User user : listUsers)
            listUsersDto.add(mapper.convertValue(user, UserDto.class));

        return listUsersDto;
    }


    public User updateUser(UserDto userDto) {
        User user = mapper.convertValue(userDto, User.class);
        return userRepository.save(user);
    }


    public void deleteUser(Integer id) {
        Optional<User> searchedUser = userRepository.findById(id);
        if (searchedUser.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email).get();
        String role = user.getRole().getName();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true, true, authorities);
    }

    public int userId(String email) {
        User user = userRepository.getUserByEmail(email).get();
        int id = user.getId();

        return (id);
    }

    public String userName(String email) {
        User user = userRepository.getUserByEmail(email).get();
        String nombre = user.getName();

        return (nombre);
    }

    public String userLastname(String email) {
        User user = userRepository.getUserByEmail(email).get();
        String lastName = user.getLastname();
        return (lastName);
    }

    public String userEmail(String email) {
        User user = userRepository.getUserByEmail(email).get();
        String eMail = user.getEmail();
        return (eMail);
    }

    public String userCity(String email){
        User user = userRepository.getUserByEmail(email).get();
        String cityUser = user.getCity().getName();
        String countryUser = user.getCity().getCountry().getName();
        return (cityUser + ", " + countryUser);
    }

    public String userRole(String email){
        User user = userRepository.getUserByEmail(email).get();
        String role = user.getRole().getName();
        return (role);
    }
}
