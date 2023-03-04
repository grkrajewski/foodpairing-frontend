package com.myapp.foodpairingfrontend.client.foodpairing;

import com.myapp.foodpairingfrontend.config.FoodpairingConfig;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DrinkIngredientBackend {

    private final RestTemplate restTemplate = new RestTemplate();
    FoodpairingConfig foodpairingConfig = new FoodpairingConfig();
    private final String endpoint = foodpairingConfig.getEndpoint();

    public List<DrinkIngredient> getDrinkIngredientsForDrinkList(String drinkId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinkingredients/for-drink/" + drinkId)
                .build()
                .encode()
                .toUri();
        DrinkIngredient[] response = restTemplate.getForObject(url, DrinkIngredient[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public DrinkIngredient saveDrinkIngredient(DrinkIngredient drinkIngredient) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinkingredients")
                .build()
                .encode()
                .toUri();
        DrinkIngredient response = restTemplate.postForObject(url, drinkIngredient, DrinkIngredient.class);
        return response;
    }

    public void updateDrinkIngredient(DrinkIngredient drinkIngredient) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinkingredients")
                .build()
                .encode()
                .toUri();
        restTemplate.put(url, drinkIngredient);
    }

    public void deleteDrinkIngredient(DrinkIngredient drinkIngredient) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(endpoint + "drinkingredients/" + drinkIngredient.getId())
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}
