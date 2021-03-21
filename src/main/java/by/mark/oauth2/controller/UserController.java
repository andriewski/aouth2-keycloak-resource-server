package by.mark.oauth2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static by.mark.oauth2.security.config.WebSecurityConfig.DEVELOPER_ROLE;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/api/v1/users";
    public static final String STATUS = "/status/check";
    public static final String BY_ID = "/{id}";

    @GetMapping(STATUS)
    public String getStatus() {
        return "Working...";
    }

    @Secured(DEVELOPER_ROLE)
    @DeleteMapping(BY_ID)
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }
}
