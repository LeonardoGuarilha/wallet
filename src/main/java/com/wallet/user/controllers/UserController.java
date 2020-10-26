package com.wallet.user.controllers;

import com.wallet.user.dto.UserDTO;
import com.wallet.user.entities.User;
import com.wallet.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        User user = modelMapper.map(userDTO, User.class);
        user = userService.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

}
