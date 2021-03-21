package by.mark.oauth2.controller;

import by.mark.oauth2.controller.dto.request.ChangeUserStatusRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static by.mark.oauth2.security.config.WebSecurityConfig.DEVELOPER_ROLE;
import static java.lang.String.format;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/api/v1/users";
    public static final String STATUS = "/status/check";
    public static final String BY_ID = "/{id}";
    public static final String CHANGE_STATUS = "/{id}/status";

    @GetMapping(STATUS)
    public String getStatus() {
        return "Working...";
    }

    @Secured(DEVELOPER_ROLE)
    @DeleteMapping(BY_ID)
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }

    // @PreAuthorize("hasRole('developer')") an example
    @PreAuthorize("hasAuthority(T(by.mark.oauth2.security.config.WebSecurityConfig).DEVELOPER_ROLE)")
    @PatchMapping(CHANGE_STATUS)
    public String deleteUser(@PathVariable String id,
                             @RequestBody ChangeUserStatusRequest request) {

        return format("Changing status to %s for user with id %s", request.getStatus(), id);
    }
}
