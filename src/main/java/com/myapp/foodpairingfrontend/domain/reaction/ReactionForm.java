package com.myapp.foodpairingfrontend.domain.reaction;

import com.myapp.foodpairingfrontend.utils.DateTimeConverter;
import com.myapp.foodpairingfrontend.view.CommentAndRatingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDateTime;

public class ReactionForm extends FormLayout {

    private CommentAndRatingView commentAndRatingView;
    private ReactionService reactionService = ReactionService.getInstance();

    private TextField id = new TextField("Reaction id");
    private TextField commentId = new TextField("Comment id");
    private TextField description = new TextField("Reaction (description)");

    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    private Binder<Reaction> binder = new Binder<>(Reaction.class);

    public ReactionForm(CommentAndRatingView commentAndRatingView) {
        this.commentAndRatingView = commentAndRatingView;
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getStyle().set("background-color", "#5F9EA0");
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.getStyle().set("background-color", "#5F9EA0");
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.getStyle().set("background-color", "#5F9EA0");
        id.setReadOnly(true);
        add(id, commentId, description, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Reaction reaction = binder.getBean();
        reaction.setCreated(DateTimeConverter.convertLocalDateTimeToString(LocalDateTime.now()));
        reactionService.saveReaction(reaction);
        commentAndRatingView.refreshReaction();
        setReaction(null);
    }

    private void update() {
        Reaction reaction = binder.getBean();
        reaction.setCreated(DateTimeConverter.convertLocalDateTimeToString(LocalDateTime.now()));
        reactionService.updateReaction(reaction);
        commentAndRatingView.refreshReaction();
        setReaction(null);
    }

    private void delete() {
        Reaction reaction = binder.getBean();
        reactionService.deleteReaction(reaction);
        commentAndRatingView.refreshReaction();
        setReaction(null);
    }

    public void setReaction(Reaction reaction) {
        binder.setBean(reaction);
        if (reaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            commentId.focus();
        }
    }
}
