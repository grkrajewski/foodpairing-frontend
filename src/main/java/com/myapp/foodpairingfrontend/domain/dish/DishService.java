package com.myapp.foodpairingfrontend.domain.dish;

import com.myapp.foodpairingfrontend.client.foodpairing.DishBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class DishService {

    private static DishService dishService;
    private final DishBackend backendClient = new DishBackend();
    private List<Dish> dishList;

    public static DishService getInstance() {
        if (dishService == null) {
            dishService = new DishService();
        }
        return dishService;
    }

    public List<Dish> getDishes() {
        try {
            dishList = backendClient.getDishList();
            return dishList;
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public Dish saveDish(Dish dish) {
        return backendClient.saveDish(dish);
    }

    public void deleteDish(Dish dish) {
        backendClient.deleteDish(dish);
    }
}
