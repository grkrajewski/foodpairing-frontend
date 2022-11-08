package com.myapp.foodpairingfrontend.mapper;

import com.myapp.foodpairingfrontend.domain.drink.TheCocktailDbDrink;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredient;

import java.util.ArrayList;
import java.util.List;

public class DrinkIngredientMapper {

    public List<DrinkIngredient> mapToDrinkIngredientList(TheCocktailDbDrink theCocktailDbDrink) {
        List<DrinkIngredient> drinkIngredientList = new ArrayList<>();
        List<DrinkIngredient> filteredDrinkIngredientList = new ArrayList<>();
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient1(), theCocktailDbDrink.getMeasure1(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient2(), theCocktailDbDrink.getMeasure2(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient3(), theCocktailDbDrink.getMeasure3(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient4(), theCocktailDbDrink.getMeasure4(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient5(), theCocktailDbDrink.getMeasure5(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient6(), theCocktailDbDrink.getMeasure6(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient7(), theCocktailDbDrink.getMeasure7(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient8(), theCocktailDbDrink.getMeasure8(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient9(), theCocktailDbDrink.getMeasure9(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient10(), theCocktailDbDrink.getMeasure10(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient11(), theCocktailDbDrink.getMeasure11(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient12(), theCocktailDbDrink.getMeasure12(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient13(), theCocktailDbDrink.getMeasure13(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient14(), theCocktailDbDrink.getMeasure14(), null));
        drinkIngredientList.add(new DrinkIngredient(null, theCocktailDbDrink.getIngredient15(), theCocktailDbDrink.getMeasure15(), null));

        for (DrinkIngredient ingredient : drinkIngredientList) {
            if(ingredient.getName() != null) {
                filteredDrinkIngredientList.add(ingredient);
            }
        }
        return filteredDrinkIngredientList;
    }
}
