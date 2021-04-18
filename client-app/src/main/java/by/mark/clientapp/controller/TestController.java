package by.mark.clientapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OAuth2AuthorizedClientService clientService;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal OidcUser principal) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );

        System.out.println("----");
        System.out.println("JwtAccessToken " + oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        System.out.println("----");
        System.out.println("Principal" + principal);
        System.out.println("Id token" + principal.getIdToken());
        System.out.println("Token" + principal.getIdToken().getTokenValue());
        System.out.println("----");

        return "It's fine :D";
    }
}
