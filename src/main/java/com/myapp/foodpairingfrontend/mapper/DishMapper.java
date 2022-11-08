package com.myapp.foodpairingfrontend.mapper;

import com.myapp.foodpairingfrontend.domain.dish.Dish;
import com.myapp.foodpairingfrontend.domain.dish.SpoonacularDish;

import java.util.ArrayList;

public class DishMapper {

    public Dish mapSpoonacularDishToDish(SpoonacularDish spoonacularDish) {
        return new Dish(
                null,
                spoonacularDish.getExternalSystemId(),
                spoonacularDish.getName(),
                spoonacularDish.getReadyInMinutes(),
                spoonacularDish.getServings(),
                spoonacularDish.getRecipeUrl(),
                new ArrayList<>()
        );
    }
}
