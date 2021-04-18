package by.mark.clientapp.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final OAuth2AuthorizedClientService clientService;

    public String getJwtToken() {
        OAuth2AuthenticationToken oauthToken = getOAuth2AuthenticationToken();
        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );

        return Optional.ofNullable(oAuth2AuthorizedClient)
                .map(OAuth2AuthorizedClient::getAccessToken)
                .map(OAuth2AccessToken::getTokenValue)
                .orElseThrow(() -> new InternalAuthenticationServiceException("User not found error"));
    }

    public HttpHeaders getHeadersWithJwtToken() {
        String jwtToken = getJwtToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);

        return httpHeaders;
    }

    private OAuth2AuthenticationToken getOAuth2AuthenticationToken() {
        return (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
