package by.mark.clientapp.controller;

import by.mark.clientapp.controller.dto.response.AlbumResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model) {
        model.addAttribute("albums", getMockAlbums());
        return "albums";
    }

    private List<AlbumResponse> getMockAlbums() {
        return List.of(
                AlbumResponse.builder()
                        .id("1")
                        .title("title1")
                        .url("https://google.com/1")
                        .build(),
                AlbumResponse.builder()
                        .id("2")
                        .title("title2")
                        .url("https://google.com/2")
                        .build()
        );
    }
}
