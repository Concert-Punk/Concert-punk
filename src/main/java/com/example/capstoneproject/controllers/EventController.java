package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Comment;
import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.Roles;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.CommentsRepository;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import com.example.capstoneproject.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {
    private final EventsRepository eventsDao;

    public EventController(EventsRepository eventsDao, UsersRepository usersDao, EmailService emailService, CommentsRepository commentsDao) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
        this.commentsDao = commentsDao;
    }

    private final UsersRepository usersDao;
    private final EmailService emailService;
    private CommentsRepository commentsDao;



    @GetMapping("/eventspage")
    public String showEvents(Model model, Comment comment) {
        List<Event> allEvents = eventsDao.findAll();
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDB = usersDao.getById(currentUser.getId());
        model.addAttribute("events", allEvents);
        model.addAttribute("comment" ,comment);
//        model.addAttribute("theCurrentUser", userInDB);
//        model.addAttribute("adminCheck", userInDB.getRole() == Roles.admin);
        return "events/usersViewEvents";
    }

//    @GetMapping("/eventspage")
//    public String viewEventsWithoutLogin(Model model, Comment comment) {
//        return "events/usersViewEvents";
//    }

    @PostMapping(value = "/deleteComment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam(name="deleteCommentText") String comment, Model model ) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUser.getId());
        commentsDao.deleteById(commentId);
        model.addAttribute("theCurrentUser", userInDB);
        return "redirect:/events";
    }


    @GetMapping("/events/{id}")
    public String showOneEvent(@PathVariable long id, Model model) {
        Event event = eventsDao.getById(id);
        model.addAttribute("eventId", id);
        model.addAttribute("event", event);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = usersDao.getById(currentUser.getId());
        model.addAttribute("theCurrentUser", userInDB);
        return "events/show";
    }

    @GetMapping("/events/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String createPost(@ModelAttribute Event eventToAdd
    ) {
        System.out.println(eventToAdd);
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventToAdd.setOwner(usersDao.getById(currentUserSession.getId()));

        emailService.prepareAndSend(
                eventToAdd,
                "new post",
                "You created a new post!"
        );

        eventsDao.save(eventToAdd);
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{id}")
    public String showEditEventForm(@PathVariable long id, Model model) {
        Event eventToEdit = eventsDao.getById(id);
        model.addAttribute("eventToEdit", eventToEdit);
        return "events/edit";
    }

    @PostMapping("/events/edit/{id}")
    public String editEvent(
            @PathVariable long id,
            @ModelAttribute Event updatedEvent
    ) {
        updatedEvent.setId(id);
        updatedEvent.setOwner(usersDao.getById(1L));
        eventsDao.save(updatedEvent);

        return "redirect:/events";

    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable long id) {

        eventsDao.deleteById(id);

        return "redirect:/events";

    }

    @RequestMapping(path = "/isLoggedIn.js", produces = "application/javascript")
    @ResponseBody
    public String isLoggedin() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "const userIsLoggedIn = false;";
        } else {
            User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userInDB = usersDao.getById(currentUserSession.getId());
            return "const userIsLoggedIn = true; const owner_id = " + userInDB.getId() + ";";
        }

    }



}