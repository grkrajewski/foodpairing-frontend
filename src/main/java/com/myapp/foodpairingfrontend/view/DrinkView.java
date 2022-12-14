package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.domain.drink.Drink;
import com.myapp.foodpairingfrontend.domain.drink.DrinkForm;
import com.myapp.foodpairingfrontend.domain.drink.DrinkService;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredient;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredientForm;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredientService;
import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("foodpairing/drink")
public class DrinkView extends VerticalLayout {

    private DrinkService drinkService = DrinkService.getInstance();
    private DrinkIngredientService drinkIngredientService = DrinkIngredientService.getInstance();
    private ButtonBar buttonBar = new ButtonBar();
    private Button addNewIngredient = new Button("Add new ingredient");

    private DrinkForm drinkForm = new DrinkForm(this);
    private DrinkIngredientForm drinkIngredientForm = new DrinkIngredientForm(this);

    private Grid<Drink> gridDrink = new Grid<>(Drink.class);
    private Grid<DrinkIngredient> gridDrinkIngredient = new Grid<>(DrinkIngredient.class);
    private TextField findByDrinkId = new TextField();

    public DrinkView() {
        add(buttonBar.createButtonBar());

        gridDrink.setColumns("id", "name", "alcoholic", "glass", "instructions");
        HorizontalLayout drinkMainContent = new HorizontalLayout(gridDrink, drinkForm);
        drinkMainContent.setSizeFull();
        gridDrink.setSizeFull();
        add(drinkMainContent);
        drinkForm.setDrink(null);
        setSizeFull();
        refreshDrink();
        gridDrink.asSingleSelect().addValueChangeListener(event -> drinkForm.setDrink(gridDrink.asSingleSelect().getValue()));

        createSearchField();
        addNewIngredient.addClickListener(e -> {
            gridDrinkIngredient.asSingleSelect().clear();
            drinkIngredientForm.setDrinkIngredient(new DrinkIngredient());
        });
        addNewIngredient.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        gridDrinkIngredient.setColumns("drinkId", "name", "measure");
        HorizontalLayout drinkIngredientMainContent = new HorizontalLayout(gridDrinkIngredient, drinkIngredientForm);
        drinkIngredientMainContent.setSizeFull();
        gridDrinkIngredient.setSizeFull();
        HorizontalLayout toolbar = new HorizontalLayout(findByDrinkId, addNewIngredient);
        add(toolbar, drinkIngredientMainContent);
        drinkIngredientForm.setDrinkIngredient(null);
        setSizeFull();
        refreshDrinkIngredient();
        gridDrinkIngredient.asSingleSelect().addValueChangeListener(event -> drinkIngredientForm.setDrinkIngredient(gridDrinkIngredient.asSingleSelect().getValue()));
    }

    public void refreshDrink() {
        gridDrink.setItems(drinkService.getDrinks());
    }

    public void refreshDrinkIngredient() {
        gridDrinkIngredient.setItems(drinkIngredientService.getDrinkIngredientsForDrink(findByDrinkId.getValue()));
    }

    public void createSearchField() {
        findByDrinkId.setPlaceholder("Search by dish id...");
        findByDrinkId.setClearButtonVisible(true);
        findByDrinkId.addValueChangeListener(e -> refreshDrinkIngredient());
    }
}
