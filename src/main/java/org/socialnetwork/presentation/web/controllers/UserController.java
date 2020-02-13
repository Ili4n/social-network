package org.socialnetwork.presentation.web.controllers;

import org.socialnetwork.domain.models.User;
import org.socialnetwork.domain.services.UserService;
import org.socialnetwork.presentation.web.binding.UserRegisterForm;
import org.socialnetwork.presentation.web.converter.UserFormConverter;

public class UserController {

    private UserService userService;
    private UserFormConverter userFormConverter;

    public UserController(UserService userService, UserFormConverter userFormConverter) {
        this.userService = userService;
        this.userFormConverter = userFormConverter;
    }

    public String registerUser(UserRegisterForm userRegisterForm) {
        User user = this.userFormConverter.convertToUser(userRegisterForm);
        this.userService.save(user);
        return "redirect://home";
    }
}
