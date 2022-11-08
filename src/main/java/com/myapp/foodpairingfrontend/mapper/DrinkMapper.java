package com.myapp.foodpairingfrontend.mapper;

import com.myapp.foodpairingfrontend.domain.drink.Drink;
import com.myapp.foodpairingfrontend.domain.drink.TheCocktailDbDrink;

import java.util.ArrayList;

public class DrinkMapper {

    public Drink mapToDrink(TheCocktailDbDrink theCocktailDbDrink) {
        return new Drink(
                null,
                theCocktailDbDrink.getExternalSystemId(),
                theCocktailDbDrink.getName(),
                theCocktailDbDrink.getAlcoholic(),
                theCocktailDbDrink.getGlass(),
                theCocktailDbDrink.getInstructions(),
                new ArrayList<>()
        );
    }
}
