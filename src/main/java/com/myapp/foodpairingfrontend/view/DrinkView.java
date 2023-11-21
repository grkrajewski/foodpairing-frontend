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
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
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
        getStyle().set("background-color", "#F5F5F5");
        add(buttonBar.createButtonBar());
        buttonBar.getDrink().getStyle().set("background-color", "#ADD8E6");
        buttonBar.getDrink().getStyle().set("color", "black");
        buttonBar.getDrink().getStyle().set("font-weight", "bold");

        gridDrink.setColumns("id", "name", "alcoholic", "glass", "instructions");
        gridDrink.getColumns().get(0).setHeader("Drink Id");
        gridDrink.getColumns().get(1).setHeader("Drink Name");
        gridDrink.getColumns().get(2).setHeader("Type Of Drink");
        gridDrink.getColumns().get(3).setHeader("Proposed Glass");
        gridDrink.getColumns().get(4).setHeader("Recipe");
        Div titleDiv = new Div();
        H3 title = new H3("DRINKS IN DATABASE");
        title.getStyle().set("font-size", "16px");
        title.getStyle().set("font-weight", "bold");
        title.getStyle().set("margin", "0");
        titleDiv.add(title);
        HeaderRow headerRow = gridDrink.prependHeaderRow();
        HeaderRow.HeaderCell titleCell = headerRow.join(gridDrink.getColumns().toArray(new Grid.Column[0]));
        titleCell.setComponent(titleDiv);
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
        addNewIngredient.getStyle().set("background-color", "#5F9EA0");
        gridDrinkIngredient.setColumns("drinkId", "name", "measure");
        gridDrinkIngredient.getColumns().get(1).setHeader("Drink Ingredient Name");
        Div titleDivDrinkIngredient = new Div();
        H3 titleDrinkIngredient = new H3("MODIFY THE DRINK INGREDIENTS BELOW AS YOU WANT");
        titleDrinkIngredient.getStyle().set("font-size", "16px");
        titleDrinkIngredient.getStyle().set("font-weight", "bold");
        titleDrinkIngredient.getStyle().set("margin", "0");
        titleDivDrinkIngredient.add(titleDrinkIngredient);
        HeaderRow headerRowDrinkIngredient = gridDrinkIngredient.prependHeaderRow();
        HeaderRow.HeaderCell titleCellDrinkIngredient = headerRowDrinkIngredient.join(gridDrinkIngredient.getColumns().toArray(new Grid.Column[0]));
        titleCellDrinkIngredient.setComponent(titleDivDrinkIngredient);
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

    private void createSearchField() {
        findByDrinkId.setPlaceholder("Search by drink id...");
        findByDrinkId.setClearButtonVisible(true);
        findByDrinkId.addValueChangeListener(e -> refreshDrinkIngredient());
    }
}
