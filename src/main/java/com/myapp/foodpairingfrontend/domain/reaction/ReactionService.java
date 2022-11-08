package com.myapp.foodpairingfrontend.domain.reaction;

import com.myapp.foodpairingfrontend.client.foodpairing.ReactionBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class ReactionService {

    private static ReactionService reactionService;
    private final ReactionBackend reactionBackend = new ReactionBackend();

    public static ReactionService getInstance() {
        if (reactionService == null) {
            reactionService = new ReactionService();
        }
        return reactionService;
    }

    public List<Reaction> getReactionsForComment(String commentId) {
        try {
            return reactionBackend.getReactionsForCommentList(commentId);
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public Reaction saveReaction(Reaction reaction) {
        return reactionBackend.saveReaction(reaction);
    }

    public void updateReaction(Reaction reaction) {
        reactionBackend.updateReaction(reaction);
    }

    public void deleteReaction(Reaction reaction) {
        reactionBackend.deleteReaction(reaction);
    }
}
