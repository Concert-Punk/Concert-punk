package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Comment;
import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.Roles;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.CommentsRepository;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventsDao;
    private CommentsRepository commentsDao;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder, EventsRepository eventsDao, CommentsRepository commentsDao) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.eventsDao = eventsDao;
        this.commentsDao = commentsDao;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setIsActive(true);
        user.setRole(Roles.user);
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String currentUserProfile(Model model) {
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUserSession.getId());
        model.addAttribute("theCurrentUser", userInDB.getRole() == Roles.admin);
        model.addAttribute("User", userInDB);
        return "users/currentUserProfile";
    }

    @GetMapping("/user/{username}/events")
    public String showUserEvents(
            @PathVariable String username,
            Model model
    ){

        User userToDisplay = usersDao.findByUsername(username);
        model.addAttribute("user",userToDisplay);

        return "users/currentUserProfile";
    }

    @GetMapping("/profile/{id}")
    public String viewedUserProfile(@PathVariable long id,Model model)
    {
        User userInDB = usersDao.getById(id);
        model.addAttribute("viewedUser", userInDB.getRole() == Roles.admin);
        return "users/viewedProfile";
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

    //    Route for adding comment
    @PostMapping(value = "/addComment/{postId}")
    public String addComment(@PathVariable Long postId, @RequestParam(name="commentText") String comment ) {
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUserSession.getId());
        Event event = eventsDao.getById(postId);
        Comment newComment = new Comment();
      newComment.setComment_text(comment);
      newComment.setUser(userInDB);
      newComment.setEvent(event);
        System.out.println(newComment);
      commentsDao.save(newComment);
        return "redirect:/events";
    }



//    User Delete Account
@PostMapping("/users/delete")
public String deleteUser() {
    User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userInDB = usersDao.getById(currentUserSession.getId());
  usersDao.delete(userInDB);
    return  "redirect:/";
}


}
