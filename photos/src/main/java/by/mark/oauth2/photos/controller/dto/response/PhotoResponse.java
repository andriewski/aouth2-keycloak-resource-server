package by.mark.oauth2.photos.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PhotoResponse {

    private final String id;
    private final String name;
    private final String lastName;
}
