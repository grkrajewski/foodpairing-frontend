package com.myapp.foodpairingfrontend.client.foodpairing;

import com.myapp.foodpairingfrontend.config.FoodpairingConfig;
import com.myapp.foodpairingfrontend.domain.dish.Dish;
import com.myapp.foodpairingfrontend.domain.dish.SpoonacularDish;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DishBackend {

    private final RestTemplate restTemplate = new RestTemplate();
    FoodpairingConfig foodpairingConfig = new FoodpairingConfig();
    private final String endpoint = foodpairingConfig.getEndpoint();

    public List<SpoonacularDish> getDishListFromSpoonacular(String nameFragment) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "spoonacular/dishes/" + nameFragment)
                .build()
                .encode()
                .toUri();
        SpoonacularDish[] response = restTemplate.getForObject(url, SpoonacularDish[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<Dish> getDishList() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "dishes")
                .build()
                .encode()
                .toUri();
        Dish[] response = restTemplate.getForObject(url, Dish[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public Dish saveDish(Dish dish) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "dishes")
                .build()
                .encode()
                .toUri();
        Dish response = restTemplate.postForObject(url, dish, Dish.class);
        return response;
    }

    public void deleteDish(Dish dish) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "dishes/" + dish.getId())
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}
