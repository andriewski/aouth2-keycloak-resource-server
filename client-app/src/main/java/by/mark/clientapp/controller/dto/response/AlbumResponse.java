package by.mark.clientapp.controller.dto.response;

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
