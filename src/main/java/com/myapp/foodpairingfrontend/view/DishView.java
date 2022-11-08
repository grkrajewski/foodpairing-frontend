package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.myapp.foodpairingfrontend.domain.dish.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("foodpairing/dish")
public class DishView extends VerticalLayout{

    private SpoonacularDishService spoonacularDishService = SpoonacularDishService.getInstance();
    private DishService dishService = DishService.getInstance();
    private ButtonBar buttonBar = new ButtonBar();

    private DishForm dishForm = new DishForm(this);
    private SpoonacularDishForm spoonacularDishForm = new SpoonacularDishForm(this);

    private Grid<SpoonacularDish> gridSpoonacularDish = new Grid<>(SpoonacularDish.class);
    private Grid<Dish> gridDish = new Grid<>(Dish.class);

    private TextField findByNameFragment = new TextField();

    public DishView() {
        add(buttonBar.createButtonBar());

        createSearchField();
        gridSpoonacularDish.setColumns("name", "readyInMinutes", "servings", "recipeUrl");
        HorizontalLayout spoonacularDishMainContent = new HorizontalLayout(gridSpoonacularDish, spoonacularDishForm);
        spoonacularDishMainContent.setSizeFull();
        gridSpoonacularDish.setSizeFull();
        add(findByNameFragment, spoonacularDishMainContent);
        setSizeFull();
        gridSpoonacularDish.asSingleSelect().addValueChangeListener(event -> spoonacularDishForm.setSpoonacularDish(gridSpoonacularDish.asSingleSelect().getValue()));

        gridDish.setColumns("name", "readyInMinutes", "servings", "recipeUrl");
        HorizontalLayout dishMainContent = new HorizontalLayout(gridDish, dishForm);
        dishMainContent.setSizeFull();
        gridDish.setSizeFull();
        add(dishMainContent);
        setSizeFull();
        refreshDish();
        gridDish.asSingleSelect().addValueChangeListener(event -> dishForm.setDish(gridDish.asSingleSelect().getValue()));
    }

    public void refreshSpoonacular() {
        gridSpoonacularDish.setItems(spoonacularDishService.getSpoonacularDishes(findByNameFragment.getValue()));
    }

    public void refreshDish() {
        gridDish.setItems(dishService.getDishes());
    }

    public void createSearchField() {
        findByNameFragment.setPlaceholder("Search dish by name...");
        findByNameFragment.setClearButtonVisible(true);
        findByNameFragment.addValueChangeListener(e -> refreshSpoonacular());
    }
}
