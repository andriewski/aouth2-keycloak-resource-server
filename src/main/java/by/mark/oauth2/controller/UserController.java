package by.mark.oauth2.controller;

import by.mark.oauth2.controller.dto.request.ChangeUserStatusRequest;
import by.mark.oauth2.controller.dto.response.GetUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @PreAuthorize("#id == #jwt.subject")
    //NOTE: @PostAuthorize can used with get user by name -> and compare id with current authenticated user
    @PostAuthorize("returnObject.body.id == #jwt.subject")
    @GetMapping(BY_ID)
    public ResponseEntity<GetUserResponse> getById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        GetUserResponse userResponse = GetUserResponse.builder()
                .id(jwt.getSubject())
                .name("empty name :)")
                .lastName("empty lastname :)")
                .build();

        return ResponseEntity.ok(userResponse);
    }

    @Secured(DEVELOPER_ROLE)
    @DeleteMapping(BY_ID)
    public String deleteUser(@PathVariable String id) {
        return "Deleted user with id " + id;
    }

    @GetMapping(STATUS)
    public String getStatus() {
        return "Working...";
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
