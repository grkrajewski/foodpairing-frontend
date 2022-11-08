package com.myapp.foodpairingfrontend.domain.comment;

import com.myapp.foodpairingfrontend.view.CommentAndRatingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.ArrayList;
import java.util.Date;

public class CommentForm extends FormLayout {

    private CommentAndRatingView commentAndRatingView;
    private CommentService commentService = CommentService.getInstance();

    private TextField id = new TextField("Comment id");
    private TextField compositionId = new TextField("Composition id");
    private TextField description = new TextField("Comment (description)");

    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    private Binder<Comment> binder = new Binder<>(Comment.class);

    public CommentForm(CommentAndRatingView commentAndRatingView) {
        this.commentAndRatingView = commentAndRatingView;
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        id.setReadOnly(true);
        add(id, compositionId, description, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Comment comment = binder.getBean();
        comment.setCreated(new Date());
        comment.setReactionList(new ArrayList<>());
        commentService.saveComment(comment);
        commentAndRatingView.refreshComment();
        setComment(null);
    }

    private void update() {
        Comment comment = binder.getBean();
        comment.setCreated(new Date());
        comment.setReactionList(new ArrayList<>());
        commentService.updateComment(comment);
        commentAndRatingView.refreshComment();
        setComment(null);
    }

    private void delete() {
        Comment comment = binder.getBean();
        commentService.deleteComment(comment);
        commentAndRatingView.refreshComment();
        commentAndRatingView.refreshReaction();
        setComment(null);
    }

    public void setComment(Comment comment) {
        binder.setBean(comment);
        if (comment == null) {
            setVisible(false);
        } else {
            setVisible(true);
            compositionId.focus();
        }
    }
}
