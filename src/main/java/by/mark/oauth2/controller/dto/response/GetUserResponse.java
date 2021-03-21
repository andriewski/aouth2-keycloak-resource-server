package by.mark.oauth2.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetUserResponse {

    private final String id;
    private final String name;
    private final String lastName;
}
