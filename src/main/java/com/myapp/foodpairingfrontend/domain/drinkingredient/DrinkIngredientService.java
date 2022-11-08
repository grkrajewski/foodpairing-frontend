package com.myapp.foodpairingfrontend.domain.drinkingredient;

import com.myapp.foodpairingfrontend.client.foodpairing.DrinkIngredientBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class DrinkIngredientService {

    private static DrinkIngredientService drinkIngredientService;
    private final DrinkIngredientBackend drinkIngredientBackend = new DrinkIngredientBackend();

    public static DrinkIngredientService getInstance() {
        if (drinkIngredientService == null) {
            drinkIngredientService = new DrinkIngredientService();
        }
        return drinkIngredientService;
    }

    public List<DrinkIngredient> getDrinkIngredientsForDrink(String drinkId) {
        try {
            return drinkIngredientBackend.getDrinkIngredientsForDrinkList(drinkId);
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public DrinkIngredient saveDrinkIngredient(DrinkIngredient drinkIngredient) {
        return drinkIngredientBackend.saveDrinkIngredient(drinkIngredient);
    }

    public void updateDrinkIngredient(DrinkIngredient drinkIngredient) {
        drinkIngredientBackend.updateDrinkIngredient(drinkIngredient);
    }

    public void deleteDrinkIngredient(DrinkIngredient drinkIngredient) {
        drinkIngredientBackend.deleteDrinkIngredient(drinkIngredient);
    }
}
