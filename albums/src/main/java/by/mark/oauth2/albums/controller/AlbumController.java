package by.mark.oauth2.albums.controller;

import by.mark.oauth2.albums.controller.dto.response.AlbumResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.mark.oauth2.albums.security.config.WebSecurityConfig.DEVELOPER_ROLE;

@RestController
@RequestMapping(AlbumController.PATH)
public class AlbumController {

    public static final String PATH = "/api/v1/albums";
    public static final String BY_ID = "/{id}";

    @Secured(DEVELOPER_ROLE)
    @GetMapping(BY_ID)
    public ResponseEntity<AlbumResponse> getById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                AlbumResponse.builder()
                        .id(id)
                        .name("test-album for user " + jwt.getSubject())
                        .build()
        );
    }
}
