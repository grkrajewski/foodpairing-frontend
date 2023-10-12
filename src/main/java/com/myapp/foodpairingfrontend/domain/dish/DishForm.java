package com.myapp.foodpairingfrontend.domain.dish;

import com.myapp.foodpairingfrontend.view.DishView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class DishForm extends FormLayout {

    private DishView dishView;
    private DishService dishService = DishService.getInstance();

    private TextField name = new TextField("Dish name");
    private TextField readyInMinutes = new TextField("Ready in minutes");
    private TextField servings = new TextField("Servings");
    private TextField recipeUrl = new TextField("Recipe URL");

    private Button delete = new Button("Delete");
    private Binder<Dish> binder = new Binder<>(Dish.class);

    public DishForm(DishView dishView) {
        this.dishView = dishView;
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.getStyle().set("background-color", "#5F9EA0");
        name.setReadOnly(true);
        readyInMinutes.setReadOnly(true);
        servings.setReadOnly(true);
        recipeUrl.setReadOnly(true);
        add(name, readyInMinutes, servings, recipeUrl, delete);
        binder.bindInstanceFields(this);
        delete.addClickListener(event -> delete());
    }

    private void delete() {
        Dish dish = binder.getBean();
        dishService.deleteDish(dish);
        dishView.refreshDish();
        setDish(null);
    }

    public void setDish(Dish dish) {
        binder.setBean(dish);
        setVisible(true);
        name.focus();
    }
}
