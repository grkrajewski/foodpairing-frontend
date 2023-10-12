package com.myapp.foodpairingfrontend.view;

import com.myapp.foodpairingfrontend.domain.comment.Comment;
import com.myapp.foodpairingfrontend.domain.comment.CommentForm;
import com.myapp.foodpairingfrontend.domain.comment.CommentService;
import com.myapp.foodpairingfrontend.domain.comment.CompositionToCommentForm;
import com.myapp.foodpairingfrontend.domain.composition.Composition;
import com.myapp.foodpairingfrontend.domain.composition.CompositionService;
import com.myapp.foodpairingfrontend.domain.reaction.Reaction;
import com.myapp.foodpairingfrontend.domain.reaction.ReactionForm;
import com.myapp.foodpairingfrontend.domain.reaction.ReactionService;
import com.myapp.foodpairingfrontend.view.component.ButtonBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("foodpairing/commentandrating")
public class CommentAndRatingView extends VerticalLayout {

    private CompositionService compositionService = CompositionService.getInstance();
    private CommentService commentService = CommentService.getInstance();
    private ReactionService reactionService = ReactionService.getInstance();
    private ButtonBar buttonBar = new ButtonBar();
    private Button addNewComment = new Button("Add new comment to composition");
    private Button addNewReaction = new Button("Add new reaction to comment");

    private CompositionToCommentForm compositionToCommentForm = new CompositionToCommentForm(this);
    private CommentForm commentForm = new CommentForm(this);
    private ReactionForm reactionForm = new ReactionForm(this);

    private Grid<Composition> gridComposition = new Grid<>(Composition.class);
    private Grid<Comment> gridComment = new Grid<>(Comment.class);
    private Grid<Reaction> gridReaction = new Grid<>(Reaction.class);
    private TextField findByCompositionId = new TextField();
    private TextField findByCommentId = new TextField();

    public CommentAndRatingView() {
        getStyle().set("background-color", "#F5F5F5");
        add(buttonBar.createButtonBar());

        gridComposition.setColumns("id", "dishId", "drinkId", "created");
        HorizontalLayout compositionToCommentMainContent = new HorizontalLayout(gridComposition, compositionToCommentForm);
        compositionToCommentMainContent.setSizeFull();
        gridComposition.setSizeFull();
        add(compositionToCommentMainContent);
        refreshComposition();
        gridComposition.asSingleSelect().addValueChangeListener(event -> compositionToCommentForm.setComposition(gridComposition.asSingleSelect().getValue()));

        createFindByCompositionField();
        addNewComment.addClickListener(e -> {
            gridComment.asSingleSelect().clear();
            commentForm.setComment(new Comment());
        });
        addNewComment.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addNewComment.getStyle().set("background-color", "#5F9EA0");
        createFindByCompositionField();
        gridComment.setColumns("compositionId", "description", "created");
        HorizontalLayout commentMainContent = new HorizontalLayout(gridComment, commentForm);
        commentMainContent.setSizeFull();
        gridComment.setSizeFull();
        HorizontalLayout commentToolbar = new HorizontalLayout(findByCompositionId, addNewComment);
        add(commentToolbar, commentMainContent);
        commentForm.setComment(null);
        setSizeFull();
        refreshComment();
        gridComment.asSingleSelect().addValueChangeListener(event -> commentForm.setComment(gridComment.asSingleSelect().getValue()));

        createFindByCommentField();
        addNewReaction.addClickListener(e -> {
            gridReaction.asSingleSelect().clear();
            reactionForm.setReaction(new Reaction());
        });
        addNewReaction.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addNewReaction.getStyle().set("background-color", "#5F9EA0");
        createFindByCommentField();
        gridReaction.setColumns("commentId", "description", "created");
        HorizontalLayout reactionMainContent = new HorizontalLayout(gridReaction, reactionForm);
        reactionMainContent.setSizeFull();
        gridReaction.setSizeFull();
        HorizontalLayout reactionToolbar = new HorizontalLayout(findByCommentId, addNewReaction);
        add(reactionToolbar, reactionMainContent);
        reactionForm.setReaction(null);
        setSizeFull();
        refreshReaction();
        gridReaction.asSingleSelect().addValueChangeListener(event -> reactionForm.setReaction(gridReaction.asSingleSelect().getValue()));
    }

    public void refreshComposition() {
        gridComposition.setItems(compositionService.getCompositions());
    }

    public void refreshComment() {
        gridComment.setItems(commentService.getCommentsForComposition(findByCompositionId.getValue()));
    }

    public void refreshReaction() {
        gridReaction.setItems(reactionService.getReactionsForComment(findByCommentId.getValue()));
    }

    public void createFindByCompositionField() {
        findByCompositionId.setPlaceholder("Search by composition id...");
        findByCompositionId.setClearButtonVisible(true);
        findByCompositionId.addValueChangeListener(e -> refreshComment());
    }

    public void createFindByCommentField() {
        findByCommentId.setPlaceholder("Search by comment id...");
        findByCommentId.setClearButtonVisible(true);
        findByCommentId.addValueChangeListener(e -> refreshReaction());
    }
}
