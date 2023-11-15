package com.myapp.foodpairingfrontend.view.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

@Getter
public class ButtonBar {
    private Button main = new Button("Main page");
    private Button dish = new Button("Dishes");
    private Button composition = new Button("Compositions");
    private Button drink = new Button("Drinks");
    private Button commentAndRating = new Button("Comments and ratings");


    public HorizontalLayout createButtonBar() {
        main.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        dish.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        composition.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        drink.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        commentAndRating.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        HorizontalLayout buttons = new HorizontalLayout(main, dish, composition, drink, commentAndRating);
        main.addClickListener(event -> main.getUI().ifPresent(ui -> ui.navigate("foodpairing/main")));
        dish.addClickListener(event -> dish.getUI().ifPresent(ui -> ui.navigate("foodpairing/dish")));
        composition.addClickListener(event -> composition.getUI().ifPresent(ui -> ui.navigate("foodpairing/composition")));
        drink.addClickListener(event -> drink.getUI().ifPresent(ui -> ui.navigate("foodpairing/drink")));
        commentAndRating.addClickListener(event -> commentAndRating.getUI().ifPresent(ui -> ui.navigate("foodpairing/commentandrating")));
        return buttons;
    }
}
