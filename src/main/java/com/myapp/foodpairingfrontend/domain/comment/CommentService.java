package com.myapp.foodpairingfrontend.domain.comment;

import com.myapp.foodpairingfrontend.client.foodpairing.CommentBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class CommentService {

    private static CommentService commentService;
    private final CommentBackend commentBackend = new CommentBackend();

    public static CommentService getInstance() {
        if (commentService == null) {
            commentService = new CommentService();
        }
        return commentService;
    }

    public List<Comment> getCommentsForComposition(String commentId) {
        try {
            return commentBackend.getCommentsForCompositionList(commentId);
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public Comment saveComment(Comment comment) {
        return commentBackend.saveComment(comment);
    }

    public void updateComment(Comment comment) {
        commentBackend.updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        commentBackend.deleteComment(comment);
    }
}
