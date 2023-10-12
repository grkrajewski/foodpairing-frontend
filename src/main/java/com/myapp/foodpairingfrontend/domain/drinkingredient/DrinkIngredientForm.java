package com.myapp.foodpairingfrontend.domain.drinkingredient;

import com.myapp.foodpairingfrontend.view.DrinkView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;

@Getter
public class DrinkIngredientForm extends FormLayout {

    private DrinkView drinkView;
    private DrinkIngredientService drinkIngredientService = DrinkIngredientService.getInstance();

    private TextField drinkId = new TextField("Drink id");
    private TextField name = new TextField("Name");
    private TextField measure = new TextField("Measure");

    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    private Binder<DrinkIngredient> binder = new Binder<>(DrinkIngredient.class);

    public DrinkIngredientForm(DrinkView drinkView) {
        this.drinkView = drinkView;
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getStyle().set("background-color", "#5F9EA0");
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.getStyle().set("background-color", "#5F9EA0");
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.getStyle().set("background-color", "#5F9EA0");
        add(drinkId, name, measure, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        DrinkIngredient drinkIngredient = binder.getBean();
        drinkIngredientService.saveDrinkIngredient(drinkIngredient);
        drinkView.refreshDrinkIngredient();
        setDrinkIngredient(null);
    }

    private void update() {
        DrinkIngredient drinkIngredient = binder.getBean();
        drinkIngredientService.updateDrinkIngredient(drinkIngredient);
        drinkView.refreshDrinkIngredient();
        setDrinkIngredient(null);
    }

    private void delete() {
        DrinkIngredient drinkIngredient = binder.getBean();
        drinkIngredientService.deleteDrinkIngredient(drinkIngredient);
        drinkView.refreshDrinkIngredient();
        setDrinkIngredient(null);
    }

    public void setDrinkIngredient(DrinkIngredient drinkIngredient) {
        binder.setBean(drinkIngredient);
        if (drinkIngredient == null) {
            setVisible(false);
        } else {
            setVisible(true);
            drinkId.focus();
        }
    }
}
