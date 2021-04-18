package by.mark.clientapp.mvc.controller.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumResponse {

    private String id;
    private String title;
    private String url;
}
