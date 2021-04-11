package by.mark.clientapp.service;

import by.mark.clientapp.config.AppConfig;
import by.mark.clientapp.controller.dto.response.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private final SecurityService securityService;

    private static final ParameterizedTypeReference<List<AlbumResponse>> ALBUMS = new ParameterizedTypeReference<>() {
    };

    public List<AlbumResponse> getAlbums() {
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(securityService.getHeadersWithJwtToken());

        ResponseEntity<List<AlbumResponse>> exchange =
                restTemplate.exchange(appConfig.getGatewayUri() + "/api/v1/albums", HttpMethod.GET, entity, ALBUMS);

        return exchange.getBody();
    }
}
