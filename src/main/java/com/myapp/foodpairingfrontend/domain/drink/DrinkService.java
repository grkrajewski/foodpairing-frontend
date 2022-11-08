package com.myapp.foodpairingfrontend.domain.drink;

import com.myapp.foodpairingfrontend.client.foodpairing.DrinkBackend;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredient;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredientService;
import com.myapp.foodpairingfrontend.mapper.DrinkIngredientMapper;
import com.myapp.foodpairingfrontend.mapper.DrinkMapper;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class DrinkService {

    private static DrinkService drinkService;
    private final DrinkBackend drinkBackend = new DrinkBackend();
    private List<Drink> drinkList;

    private DrinkIngredientService drinkIngredientService = DrinkIngredientService.getInstance();
    private TheCocktailDbDrinkService theCocktailDbDrinkService = TheCocktailDbDrinkService.getInstance();
    private DrinkMapper drinkMapper = new DrinkMapper();
    private DrinkIngredientMapper drinkIngredientMapper = new DrinkIngredientMapper();

    public static DrinkService getInstance() {
        if (drinkService == null) {
            drinkService = new DrinkService();
        }
        return drinkService;
    }

    public List<Drink> getDrinks() {
        try {
            drinkList = drinkBackend.getDrinkList();
            return drinkList;
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public Drink saveDrink(Drink drink) {
        return drinkBackend.saveDrink(drink);
    }

    public Drink saveDrinkWithIngredients() {
        TheCocktailDbDrink theCocktailDbDrink = theCocktailDbDrinkService.getTheCocktailDbDrinks().get(0);
        Drink drink = drinkMapper.mapToDrink(theCocktailDbDrink);
        Drink savedDrink = drinkService.saveDrink(drink);
        List<DrinkIngredient> drinkIngredientList = drinkIngredientMapper.mapToDrinkIngredientList(theCocktailDbDrink);
        for (DrinkIngredient ingredient : drinkIngredientList) {
            ingredient.setDrinkId(savedDrink.getId());
            drinkIngredientService.saveDrinkIngredient(ingredient);
        }
        return savedDrink;
    }

    public void updateDrink(Drink drink) {
        drinkBackend.updateDrink(drink);
    }

    public void deleteDrink(Long drinkId) {
        drinkBackend.deleteDrink(drinkId);
    }
}
