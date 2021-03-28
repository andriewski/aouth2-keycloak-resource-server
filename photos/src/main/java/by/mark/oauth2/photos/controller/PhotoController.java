package by.mark.oauth2.photos.controller;

import by.mark.oauth2.photos.controller.dto.response.PhotoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.mark.oauth2.photos.security.config.WebSecurityConfig.DEVELOPER_ROLE;

@RestController
@RequestMapping(PhotoController.PATH)
public class PhotoController {

    public static final String PATH = "/api/v1/photos";
    public static final String BY_ID = "/{id}";

    @Secured(DEVELOPER_ROLE)
    @GetMapping(BY_ID)
    public ResponseEntity<PhotoResponse> getById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                PhotoResponse.builder()
                        .id(id)
                        .name("test-photo for user " + jwt.getSubject())
                        .build()
        );
    }
}
