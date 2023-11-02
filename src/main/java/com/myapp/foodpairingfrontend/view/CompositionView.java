package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.domain.composition.Composition;
import com.myapp.foodpairingfrontend.domain.composition.CompositionForm;
import com.myapp.foodpairingfrontend.domain.composition.CompositionService;
import com.myapp.foodpairingfrontend.domain.composition.DishToCompositionForm;
import com.myapp.foodpairingfrontend.domain.dish.Dish;
import com.myapp.foodpairingfrontend.domain.dish.DishService;
import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
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
        getStyle().set("background-color", "#F5F5F5");
        add(buttonBar.createButtonBar());

        gridDish.setColumns("id", "name", "readyInMinutes", "servings", "recipeUrl");
        Div titleDivDish = new Div();
        H3 titleDish = new H3("DISHES IN \"FOODPAIRING\" DATABASE");
        titleDish.getStyle().set("font-size", "16px");
        titleDish.getStyle().set("font-weight", "bold");
        titleDish.getStyle().set("margin", "0");
        titleDivDish.add(titleDish);
        HeaderRow headerRowDish = gridDish.prependHeaderRow();
        HeaderRow.HeaderCell titleCellDish = headerRowDish.join(gridDish.getColumns().toArray(new Grid.Column[0]));
        titleCellDish.setComponent(titleDivDish);
        HorizontalLayout dishToCompositionMainContent = new HorizontalLayout(gridDish, dishToCompositionForm);
        dishToCompositionMainContent.setSizeFull();
        gridDish.setSizeFull();
        add(dishToCompositionMainContent);
        refreshDish();
        gridDish.asSingleSelect().addValueChangeListener(event -> dishToCompositionForm.setDish(gridDish.asSingleSelect().getValue()));

        gridComposition.setColumns("id", "dishId", "drinkId", "created");
        Div titleDivComposition = new Div();
        H3 titleComposition = new H3("COMPOSITIONS");
        titleComposition.getStyle().set("font-size", "16px");
        titleComposition.getStyle().set("font-weight", "bold");
        titleComposition.getStyle().set("margin", "0");
        titleDivComposition.add(titleComposition);
        HeaderRow headerRowComposition = gridComposition.prependHeaderRow();
        HeaderRow.HeaderCell titleCellComposition = headerRowComposition.join(gridComposition.getColumns().toArray(new Grid.Column[0]));
        titleCellComposition.setComponent(titleDivComposition);
        HorizontalLayout dishMainContent = new HorizontalLayout(gridComposition, compositionForm);
        dishMainContent.setSizeFull();
        gridComposition.setSizeFull();
        add(dishMainContent);
        compositionForm.setComposition(null);
        setSizeFull();
        refreshComposition();
        gridComposition.asSingleSelect().addValueChangeListener(event -> compositionForm.setComposition(gridComposition.asSingleSelect().getValue()));
    }

    private void refreshDish() {
        gridDish.setItems(dishService.getDishes());
    }

    public void refreshComposition() {
        gridComposition.setItems(compositionService.getCompositions());
    }
}
