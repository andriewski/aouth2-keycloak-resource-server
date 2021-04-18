package by.mark.clientapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Optional.ofNullable;

@Controller
public class HomePageController {

    @GetMapping("/home")
    public String displayHomePage(Model model, @AuthenticationPrincipal OAuth2User user) {
        ofNullable(user)
                .map(it -> it.getAttribute("name"))
                .ifPresent(name -> model.addAttribute("name", name));

        return "home";
    }
}
