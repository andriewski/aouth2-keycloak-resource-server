package by.mark.clientapp.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AlbumResponse {

    private final String id;
    private final String title;
    private final String url;
}
