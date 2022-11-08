package com.myapp.foodpairingfrontend.client.foodpairing;

import com.myapp.foodpairingfrontend.config.FoodpairingConfig;
import com.myapp.foodpairingfrontend.domain.composition.Composition;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompositionBackend {

    private final RestTemplate restTemplate = new RestTemplate();
    FoodpairingConfig foodpairingConfig = new FoodpairingConfig();
    private final String endpoint = foodpairingConfig.getEndpoint();

    public List<Composition> getCompositionList() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "compositions")
                .build()
                .encode()
                .toUri();
        Composition[] response = restTemplate.getForObject(url, Composition[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public Composition createComposition(Composition composition) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "compositions")
                .build()
                .encode()
                .toUri();
        Composition response = restTemplate.postForObject(url, composition, Composition.class);
        return response;
    }

    public void updateComposition(Composition composition) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "compositions")
                .build()
                .encode()
                .toUri();
        restTemplate.put(url, composition);
    }

    public void deleteComposition(Composition composition) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "compositions/" + composition.getId())
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}
