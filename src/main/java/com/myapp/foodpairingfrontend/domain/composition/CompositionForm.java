package com.myapp.foodpairingfrontend.domain.composition;

import com.myapp.foodpairingfrontend.domain.drink.Drink;
import com.myapp.foodpairingfrontend.domain.drink.DrinkService;
import com.myapp.foodpairingfrontend.view.CompositionView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.ArrayList;
import java.util.Date;

public class CompositionForm extends FormLayout {

    private CompositionView compositionView;
    private CompositionService compositionService = CompositionService.getInstance();
    private DrinkService drinkService = DrinkService.getInstance();

    private TextField id = new TextField("Composition id");
    private TextField dishId = new TextField("Dish id");
    private TextField drinkId = new TextField("Drink id");
    private TextField created = new TextField("Created");

    private Button changeDrink = new Button("Change drink");
    private Button delete = new Button("Delete composition");
    private Binder<Composition> binder = new Binder<>(Composition.class);

    public CompositionForm(CompositionView compositionView) {
        this.compositionView = compositionView;
        HorizontalLayout buttons = new HorizontalLayout(changeDrink, delete);
        changeDrink.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        id.setReadOnly(true);
        dishId.setReadOnly(true);
        drinkId.setReadOnly(true);
        created.setReadOnly(true);
        add(id, dishId, drinkId, created, buttons);
        binder.bindInstanceFields(this);
        delete.addClickListener(event -> delete());
        changeDrink.addClickListener(event -> changeDrink());
    }

    public void changeDrink() {
        Composition composition = binder.getBean();
        Drink savedDrink = drinkService.saveDrinkWithIngredients();
        Composition updatedComposition = new Composition(
                composition.getId(),
                composition.getDishId(),
                savedDrink.getId(),
                new Date(),
                new ArrayList<>()
        );
        compositionService.updateComposition(updatedComposition);
        drinkService.deleteDrink(composition.getDrinkId());
        compositionView.refreshComposition();
    }

    private void delete() {
        Composition composition = binder.getBean();
        compositionService.deleteComposition(composition);
        compositionView.refreshComposition();
        setComposition(null);
    }

    public void setComposition(Composition composition) {
        binder.setBean(composition);
        setVisible(true);
        created.focus();
    }
}
