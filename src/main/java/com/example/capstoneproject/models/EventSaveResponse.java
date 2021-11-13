package com.example.capstoneproject.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EventSaveResponse {
    @Getter @Setter
    List<String> images;

//    @Getter @Setter
//    List<String> ticketLinks;

    @Getter @Setter
    Event event;

}
