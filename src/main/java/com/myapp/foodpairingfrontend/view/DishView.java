package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.myapp.foodpairingfrontend.domain.dish.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
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
        getStyle().set("background-color", "#F5F5F5");
        add(buttonBar.createButtonBar());
        buttonBar.getDish().getStyle().set("background-color", "#ADD8E6");
        buttonBar.getDish().getStyle().set("color", "black");
        buttonBar.getDish().getStyle().set("font-weight", "bold");

        createSearchField();
        gridSpoonacularDish.setColumns("name", "readyInMinutes", "servings", "recipeUrl");
        gridSpoonacularDish.getColumns().get(0).setHeader("Dish Name");
        Div titleDivSpoonacularDish = new Div();
        H3 titleSpoonacularDish = new H3("SEARCH DISHES IN EXTERNAL DATABASE");
        titleSpoonacularDish.getStyle().set("font-size", "16px");
        titleSpoonacularDish.getStyle().set("font-weight", "bold");
        titleSpoonacularDish.getStyle().set("margin", "0");
        titleDivSpoonacularDish.add(titleSpoonacularDish);
        HeaderRow headerRowSpoonacularDish = gridSpoonacularDish.prependHeaderRow();
        HeaderRow.HeaderCell titleCellSpoonacularDish = headerRowSpoonacularDish.join(gridSpoonacularDish.getColumns().toArray(new Grid.Column[0]));
        titleCellSpoonacularDish.setComponent(titleDivSpoonacularDish);
        HorizontalLayout spoonacularDishMainContent = new HorizontalLayout(gridSpoonacularDish, spoonacularDishForm);
        spoonacularDishMainContent.setSizeFull();
        gridSpoonacularDish.setSizeFull();
        add(findByNameFragment, spoonacularDishMainContent);
        setSizeFull();
        gridSpoonacularDish.asSingleSelect().addValueChangeListener(event -> spoonacularDishForm.setSpoonacularDish(gridSpoonacularDish.asSingleSelect().getValue()));

        gridDish.setColumns("name", "readyInMinutes", "servings", "recipeUrl");
        gridDish.getColumns().get(0).setHeader("Dish Name");
        Div titleDivDish = new Div();
        H3 titleDish = new H3("DISHES IN \"FOODPAIRING\" DATABASE");
        titleDish.getStyle().set("font-size", "16px");
        titleDish.getStyle().set("font-weight", "bold");
        titleDish.getStyle().set("margin", "0");
        titleDivDish.add(titleDish);
        HeaderRow headerRowDish = gridDish.prependHeaderRow();
        HeaderRow.HeaderCell titleCellDish = headerRowDish.join(gridDish.getColumns().toArray(new Grid.Column[0]));
        titleCellDish.setComponent(titleDivDish);
        HorizontalLayout dishMainContent = new HorizontalLayout(gridDish, dishForm);
        dishMainContent.setSizeFull();
        gridDish.setSizeFull();
        add(dishMainContent);
        setSizeFull();
        refreshDish();
        gridDish.asSingleSelect().addValueChangeListener(event -> dishForm.setDish(gridDish.asSingleSelect().getValue()));
    }

    private void refreshSpoonacular() {
        gridSpoonacularDish.setItems(spoonacularDishService.getSpoonacularDishes(findByNameFragment.getValue()));
    }

    public void refreshDish() {
        gridDish.setItems(dishService.getDishes());
    }

    private void createSearchField() {
        findByNameFragment.setPlaceholder("Search dish by name...");
        findByNameFragment.setClearButtonVisible(true);
        findByNameFragment.addValueChangeListener(e -> refreshSpoonacular());
    }
}
