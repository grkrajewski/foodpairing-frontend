package com.myapp.foodpairingfrontend.domain.dish;

import com.myapp.foodpairingfrontend.client.foodpairing.DishBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class SpoonacularDishService {

    private static SpoonacularDishService spoonacularDishService;
    private final DishBackend dishBackend = new DishBackend();
    private List<SpoonacularDish> spoonacularDishList;

    public static SpoonacularDishService getInstance() {
        if (spoonacularDishService == null) {
            spoonacularDishService = new SpoonacularDishService();
        }
        return spoonacularDishService;
    }

    public List<SpoonacularDish> getSpoonacularDishes(String nameFragment) {
        try {
            spoonacularDishList = dishBackend.getDishListFromSpoonacular(nameFragment);
            return spoonacularDishList;
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }
}
