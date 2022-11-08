package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.domain.composition.Composition;
import com.myapp.foodpairingfrontend.domain.composition.CompositionForm;
import com.myapp.foodpairingfrontend.domain.composition.CompositionService;
import com.myapp.foodpairingfrontend.domain.composition.DishToCompositionForm;
import com.myapp.foodpairingfrontend.domain.dish.Dish;
import com.myapp.foodpairingfrontend.domain.dish.DishService;
import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("foodpairing/composition")
public class CompositionView extends VerticalLayout {

    private DishService dishService = DishService.getInstance();
    private CompositionService compositionService = CompositionService.getInstance();
    private ButtonBar buttonBar = new ButtonBar();

    private DishToCompositionForm dishToCompositionForm = new DishToCompositionForm(this);
    private CompositionForm compositionForm = new CompositionForm(this);

    private Grid<Dish> gridDish = new Grid<>(Dish.class);
    private Grid<Composition> gridComposition = new Grid<>(Composition.class);

    public CompositionView() {
        add(buttonBar.createButtonBar());

        gridDish.setColumns("id", "name", "readyInMinutes", "servings", "recipeUrl");
        HorizontalLayout dishToCompositionMainContent = new HorizontalLayout(gridDish, dishToCompositionForm);
        dishToCompositionMainContent.setSizeFull();
        gridDish.setSizeFull();
        add(dishToCompositionMainContent);
        refreshDish();
        gridDish.asSingleSelect().addValueChangeListener(event -> dishToCompositionForm.setDish(gridDish.asSingleSelect().getValue()));

        gridComposition.setColumns("id", "dishId", "drinkId", "created");
        HorizontalLayout dishMainContent = new HorizontalLayout(gridComposition, compositionForm);
        dishMainContent.setSizeFull();
        gridComposition.setSizeFull();
        add(dishMainContent);
        compositionForm.setComposition(null);
        setSizeFull();
        refreshComposition();
        gridComposition.asSingleSelect().addValueChangeListener(event -> compositionForm.setComposition(gridComposition.asSingleSelect().getValue()));
    }

    public void refreshDish() {
        gridDish.setItems(dishService.getDishes());
    }

    public void refreshComposition() {
        gridComposition.setItems(compositionService.getCompositions());
    }
}
