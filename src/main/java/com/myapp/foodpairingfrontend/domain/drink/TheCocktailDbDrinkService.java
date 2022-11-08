package com.myapp.foodpairingfrontend.domain.drink;

import com.myapp.foodpairingfrontend.client.foodpairing.DrinkBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class TheCocktailDbDrinkService {

    private static TheCocktailDbDrinkService theCocktailDbDrinkService;
    private final DrinkBackend drinkBackend = new DrinkBackend();
    private List<TheCocktailDbDrink> theCocktailDbDrinkList;

    public static TheCocktailDbDrinkService getInstance() {
        if (theCocktailDbDrinkService == null) {
            theCocktailDbDrinkService = new TheCocktailDbDrinkService();
        }
        return theCocktailDbDrinkService;
    }

    public List<TheCocktailDbDrink> getTheCocktailDbDrinks() {
        try {
            theCocktailDbDrinkList = drinkBackend.getDrinkListFromTheCocktailDb();
            return theCocktailDbDrinkList;
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }
}
