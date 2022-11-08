package com.myapp.foodpairingfrontend.client.foodpairing;

import com.myapp.foodpairingfrontend.config.FoodpairingConfig;
import com.myapp.foodpairingfrontend.domain.reaction.Reaction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReactionBackend {

    private final RestTemplate restTemplate = new RestTemplate();
    FoodpairingConfig foodpairingConfig = new FoodpairingConfig();
    private final String endpoint = foodpairingConfig.getEndpoint();

    public List<Reaction> getReactionsForCommentList(String commentId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "reactions/" + commentId)
                .build()
                .encode()
                .toUri();
        Reaction[] response = restTemplate.getForObject(url, Reaction[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public Reaction saveReaction(Reaction reaction) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "reactions")
                .build()
                .encode()
                .toUri();
        Reaction response = restTemplate.postForObject(url, reaction, Reaction.class);
        return response;
    }

    public void updateReaction(Reaction reaction) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "reactions")
                .build()
                .encode()
                .toUri();
        restTemplate.put(url, reaction);
    }

    public void deleteReaction(Reaction reaction) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "reactions/" + reaction.getId())
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}
