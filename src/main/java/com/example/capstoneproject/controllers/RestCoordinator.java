package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.EventPhoto;
import com.example.capstoneproject.models.EventSaveResponse;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.EventPhotosRepository;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestCoordinator {

    private EventsRepository eventsDao;
    private UsersRepository usersDao;
    private EventPhotosRepository eventPhotosDao;

    public RestCoordinator(EventsRepository eventsDao, UsersRepository usersDao, EventPhotosRepository eventPhotosDao) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
        this.eventPhotosDao = eventPhotosDao;
    }

    @PostMapping(value="/saveEvent",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Event saveEvent(@RequestBody(required = false) EventSaveResponse resp) {
        Event event = resp.getEvent();
        EventPhoto eventPhoto = new EventPhoto();
        System.out.println(resp.getImages().get(0));
        System.out.println(event);
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventPhoto.setImageUrl(resp.getImages().get(0));
        event.setOwner(currentUserSession);
        eventPhoto.setUser(currentUserSession);
        event = eventsDao.save(event);
        eventPhoto.setEvent(event);
        eventPhotosDao.save(eventPhoto);
        return event;
    }

//    @PostMapping(value="/saveEvent",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public EventPhoto saveEventPhoto(@RequestBody(required = false) EventPhoto eventPhoto) {
//        System.out.println(eventPhoto);
//        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        eventPhoto.setOwner(currentUserSession);
//        return eventPhotosDao.save(eventPhoto);
//    }
}
