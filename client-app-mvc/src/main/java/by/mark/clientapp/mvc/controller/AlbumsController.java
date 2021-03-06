package by.mark.clientapp.mvc.controller;

import by.mark.clientapp.mvc.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AlbumsController {

    private final AlbumService albumService;

    @GetMapping("/albums")
    public String getAlbums(Model model) {
        model.addAttribute("albums", albumService.getAlbums());
        return "albums";
    }
}
