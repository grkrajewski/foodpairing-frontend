package com.myapp.foodpairingfrontend.client.foodpairing;

import com.myapp.foodpairingfrontend.config.FoodpairingConfig;
import com.myapp.foodpairingfrontend.domain.drink.Drink;
import com.myapp.foodpairingfrontend.domain.drink.TheCocktailDbDrink;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DrinkBackend {

    private final RestTemplate restTemplate = new RestTemplate();
    FoodpairingConfig foodpairingConfig = new FoodpairingConfig();
    private final String endpoint = foodpairingConfig.getEndpoint();

    public List<TheCocktailDbDrink> getDrinkListFromTheCocktailDb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "thecocktaildb/randomdrink")
                .build()
                .encode()
                .toUri();
        TheCocktailDbDrink[] response = restTemplate.getForObject(url, TheCocktailDbDrink[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<Drink> getDrinkList() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinks")
                .build()
                .encode()
                .toUri();
        Drink[] response = restTemplate.getForObject(url, Drink[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public Drink saveDrink(Drink drink) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinks")
                .build()
                .encode()
                .toUri();
        Drink response = restTemplate.postForObject(url, drink, Drink.class);
        return response;
    }

    public void updateDrink(Drink drink) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinks")
                .build()
                .encode()
                .toUri();
        restTemplate.put(url, drink);
    }

    public void deleteDrink(Long drinkId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinks/" + drinkId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}
