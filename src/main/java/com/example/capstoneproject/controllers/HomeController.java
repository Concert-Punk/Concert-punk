package com.example.capstoneproject.controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Value("${MAPBOXKEY}")
    private String MapboxKey;
    @Value("${TICKETMASTERKEY}")
    private String ticketMasterKey;
    @Value("${SEATGEEKKEY}")
    private String seatGeekKey;
    @Value("${YOUTUBEKEY}")
    private String youtubeKey;
    @Value("${FILESTACKKEY}")
    private String fileStackKey;
    @Value("${SPOTIFYCLIENTID}")
    private String spotifyClientId;
    @Value("${SPOTIFYCLIENTSECRET}")
    private String spotifyClientSecret;

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String fileStackKey() {
        System.out.println(fileStackKey);
        return "const FileStackApiKey = `" + fileStackKey + "`;\n const ticketMasterKey = `" + ticketMasterKey + "`; "
                + " const spotifyClientId = `" + spotifyClientId +
                "`;\n const spotifyClientSecret = `" + spotifyClientSecret +
                "`;\n const youtubeKey = `" + youtubeKey +
                "`;\n const mapboxKey = `" + MapboxKey +
                "`;\n const seatGeek = `" + seatGeekKey + "`"
                ;
    }


    @GetMapping("/")
    public String homePage() {
        return "home";
    }


}
