package by.mark.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/api/v1/users";
    public static final String STATUS = "/status/check";

    @GetMapping(STATUS)
    public String getStatus() {
        return "Working...";
    }
}
