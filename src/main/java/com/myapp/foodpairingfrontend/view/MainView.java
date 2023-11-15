package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.domain.composition.Composition;
import com.myapp.foodpairingfrontend.domain.composition.CompositionService;
import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("foodpairing/main")
public class MainView extends VerticalLayout {

    private ButtonBar buttonBar = new ButtonBar();

    private CompositionService compositionService = CompositionService.getInstance();

    private Grid<Composition> gridComposition = new Grid<>(Composition.class);

    public MainView() {
        add(buttonBar.createButtonBar());
        buttonBar.getMain().getStyle().set("background-color", "#ADD8E6");
        buttonBar.getMain().getStyle().set("color", "black");
        buttonBar.getMain().getStyle().set("font-weight", "bold");

        add("Choose your favourite dish, get a random drink to it :) ...... and check how it tastes together......, then comment compositions which were created. ");

        gridComposition.setColumns("id", "dishId", "drinkId", "created");
        Div titleDivComposition = new Div();
        H3 titleComposition = new H3("ALREADY SAVED COMPOSITIONS");
        titleComposition.getStyle().set("font-size", "16px");
        titleComposition.getStyle().set("font-weight", "bold");
        titleComposition.getStyle().set("margin", "0");
        titleDivComposition.add(titleComposition);
        HeaderRow headerRowComposition = gridComposition.prependHeaderRow();
        HeaderRow.HeaderCell titleCellComposition = headerRowComposition.join(gridComposition.getColumns().toArray(new Grid.Column[0]));
        titleCellComposition.setComponent(titleDivComposition);
        HorizontalLayout mainContent = new HorizontalLayout(gridComposition);
        mainContent.setSizeFull();
        gridComposition.setSizeFull();
        add(mainContent);
        setSizeFull();
        refreshComposition();
    }

    private void refreshComposition() {
        gridComposition.setItems(compositionService.getCompositions());
    }
}