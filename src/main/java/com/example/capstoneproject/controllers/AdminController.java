package com.example.capstoneproject.controllers;


import com.example.capstoneproject.models.Comment;
import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.Roles;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.CommentsRepository;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private final UsersRepository userDao;
    private CommentsRepository commentsDao;
    private EventsRepository eventsDao;

    public AdminController(UsersRepository userDao, CommentsRepository commentsDao, EventsRepository eventsDao) {
        this.userDao = userDao;
        this.commentsDao = commentsDao;
        this.eventsDao = eventsDao;
    }


    @GetMapping("/admin/AdminHome")
    public String adminHome(Model model) {
        List<User> usersToShow = userDao.findAll();
        model.addAttribute("users", usersToShow);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = userDao.getById(currentUser.getId());
        model.addAttribute("theCurrentUser", userInDB.getRole() == Roles.admin);
        return checkifAdmin("admin/AdminHome");
    }

    public String checkifAdmin(String originalTemplate) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = userDao.getById(currentUser.getId());
        if (userInDB.getRole() == Roles.admin) {
            return originalTemplate;

        } else {
            return "redirect:/";
        }
    }
    @PostMapping("/admin/profile/{id}")
        public String changeStatus (@PathVariable long id, @RequestParam(name="status") String status){
        User userInDB = userDao.getById(id);
        userInDB.setIsActive(status.equals("enable"));
        userDao.save(userInDB);
        return  "redirect:/admin/AdminHome";
        }

    @PostMapping(value = "/deleteComment/{postId}")
    public String deleteComment() {


        return "redirect:/events";
    }

}
