package com.myapp.foodpairingfrontend.domain.comment;

import com.myapp.foodpairingfrontend.domain.composition.Composition;
import com.myapp.foodpairingfrontend.view.CommentAndRatingView;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CompositionToCommentForm extends FormLayout {

    private CommentAndRatingView commentAndRatingView;

    private TextField id = new TextField("Composition id");
    private TextField dishId = new TextField("Dish id");
    private TextField drinkId = new TextField("Drink id");
    private TextField created = new TextField("Created");

    private Binder<Composition> binder = new Binder<>(Composition.class);

    public CompositionToCommentForm(CommentAndRatingView commentAndRatingView) {
        this.commentAndRatingView = commentAndRatingView;
        id.setReadOnly(true);
        dishId.setReadOnly(true);
        drinkId.setReadOnly(true);
        created.setReadOnly(true);
        add(id, dishId, drinkId, created);
        binder.bindInstanceFields(this);
    }

    public void setComposition(Composition composition) {
        binder.setBean(composition);
        setVisible(true);
        id.focus();
    }
}
