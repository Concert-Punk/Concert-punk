package com.example.capstoneproject.controllers;
<<<<<<< HEAD
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
>>>>>>> 84f9357dc6a6a675bac7c56f375dcefef212f36b
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
<<<<<<< HEAD
    private UsersRepository usersDao;

    public HomeController(UsersRepository usersDao) {
        this.usersDao = usersDao;
    }

=======
>>>>>>> 84f9357dc6a6a675bac7c56f375dcefef212f36b
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
<<<<<<< HEAD
//        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userInDB = usersDao.getById(currentUserSession.getId());
//        model.addAttribute("theCurrentUser", userInDB);
        return "home";
    }

    @RequestMapping(path = "/isLoggedIn.js", produces = "application/javascript")
    @ResponseBody
    public String isLoggedin(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "const userIsLoggedIn = false;";
        }else{
            User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userInDB = usersDao.getById(currentUserSession.getId());
            return "const userIsLoggedIn = true; const owner_id = " + userInDB.getId() +";";
        }
    }

=======
        return "home";
    }

>>>>>>> 84f9357dc6a6a675bac7c56f375dcefef212f36b

}
