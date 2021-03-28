package by.mark.oauth2.resourceserver.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetUserResponse {

    private final String id;
    private final String name;
    private final String lastName;
}
