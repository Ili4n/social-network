package org.socialnetwork.presentation.web.controllers;

import org.socialnetwork.domain.user.User;
import org.socialnetwork.domain.user.UserService;
import org.socialnetwork.presentation.web.binding.UserRegisterForm;
import org.socialnetwork.presentation.web.converter.UserFormConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserFormConverter userFormConverter;

    @Autowired
    public UserController(UserService userService, UserFormConverter userFormConverter) {
        this.userService = userService;
        this.userFormConverter = userFormConverter;
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registerForm", new UserRegisterForm());
        modelAndView.setViewName("/user/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(UserRegisterForm userRegisterForm) {
        User user = this.userFormConverter.convertToUser(userRegisterForm);
        this.userService.save(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    @GetMapping("/search-profile")
    public ModelAndView Search(@RequestParam(value = "firstName",required = false) String firstName, @RequestParam(value = "email", required = false) String email) {
        List<User> users = this.userService.findUser(firstName, email);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/search-profile");
        modelAndView.addObject("users",users);
        return modelAndView;
    }
}
