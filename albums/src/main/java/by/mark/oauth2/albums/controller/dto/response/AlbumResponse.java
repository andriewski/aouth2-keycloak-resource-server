package by.mark.oauth2.albums.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AlbumResponse {

    private final String id;
    private final String title;
    private final String url;
}
