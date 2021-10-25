package com.example.capstoneproject.controllers;


import com.example.capstoneproject.models.Roles;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class AdminController {
    private final UsersRepository userDao;

    public AdminController(UsersRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/admin/AdminHome")
    public String adminHome(Model model) {
        List<User> usersToShow = userDao.findAll();
        model.addAttribute("users", usersToShow);
        return checkifAdmin("admin/AdminHome");
    }

    public String checkifAdmin(String originalTemplate) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = userDao.getById(currentUser.getId());
        System.out.println(userInDB.getRole());
        if (userInDB.getRole() == Roles.admin) {
            return originalTemplate;
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/admin/profile/{id}")
        public String changeStatus (@PathVariable long id, @RequestParam(name="status") String status){
        User userInDB = userDao.getById(id);
        System.out.println(userInDB.getIsActive());
        System.out.println(status);
        System.out.println(userInDB);
        userInDB.setIsActive(status.equals("enable"));
        userDao.save(userInDB);
        return  "redirect:/admin/AdminHome";
        }



}
