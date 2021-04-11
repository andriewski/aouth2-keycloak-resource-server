package by.mark.oauth2.resourceserver.controller;

import by.mark.oauth2.resourceserver.controller.request.ChangeUserStatusRequest;
import by.mark.oauth2.resourceserver.controller.response.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static by.mark.oauth2.resourceserver.security.config.WebSecurityConfig.DEVELOPER_ROLE;
import static java.lang.String.format;

@RestController
@RequestMapping(UserController.PATH)
@RequiredArgsConstructor
public class UserController {

    public static final String PATH = "/api/v1/users";
    public static final String STATUS = "/status/check";
    public static final String BY_ID = "/{id}";
    public static final String CHANGE_STATUS = "/{id}/status";

    private final Environment env;

    @PreAuthorize("#id == #jwt.subject")
    //NOTE: @PostAuthorize can used with get user by name -> and compare id with current authenticated user
    @PostAuthorize("returnObject.body.id == #jwt.subject")
    @GetMapping(BY_ID)
    public ResponseEntity<GetUserResponse> getById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                GetUserResponse.builder()
                        .id("c8e71a2c-e3c5-438e-956d-a2b8ff64a79d")
                        .name("user2")
                        .lastName("user2")
                        .build()
        );
    }

    @Secured(DEVELOPER_ROLE)
    @DeleteMapping(BY_ID)
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }

    @GetMapping(STATUS)
    public String getStatus() {
        return format("Working on port %s...", env.getProperty("local.server.port"));
    }

    //    @PreAuthorize("hasAuthority(T(by.mark.oauth2.security.config.WebSecurityConfig).DEVELOPER_ROLE)") an example
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    @PatchMapping(CHANGE_STATUS)
    public String changeStatus(@PathVariable String id,
                               @RequestBody ChangeUserStatusRequest request,
                               @AuthenticationPrincipal Jwt jwt) {

        return format(
                "Changing status to '%s' for user with id=%s and subject=%s",
                request.getStatus(), id, jwt.getSubject()
        );
    }
}
