package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Roles;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setIsActive(true);
        user.setRole(Roles.user);
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String currentUserProfile()
    {
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUserSession.getId());

        return "users/currentUserProfile";
    }

    @GetMapping("/users/edit")
    public String showEditUserForm(Model model) {
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = usersDao.getById(currentUserSession.getId());
        model.addAttribute("userToEdit", userToEdit);
        return "users/edit";
    }

    @PostMapping("/users/edit")
    public String editUser(
            @ModelAttribute User userToEdit
    ) {
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUserSession.getId());
        userInDB.setUsername(userToEdit.getUsername());
        userInDB.setEmail(userToEdit.getEmail());
        usersDao.save(userInDB);

        return "redirect:/profile";

    }

//    @GetMapping("/user/posts")
//    public String showUserPosts(
//            Model model
//    ){
//
//        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user",currentUserSession);
//
//        return "users/displayPost";
//    }



}