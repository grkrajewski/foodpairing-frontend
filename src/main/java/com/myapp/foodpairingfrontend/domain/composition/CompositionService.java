package com.myapp.foodpairingfrontend.domain.composition;

import com.myapp.foodpairingfrontend.client.foodpairing.CompositionBackend;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

public class CompositionService {

    private static CompositionService compositionService;
    private final CompositionBackend compositionBackend = new CompositionBackend();
    private List<Composition> compositionList;

    public static CompositionService getInstance() {
        if (compositionService == null) {
            compositionService = new CompositionService();
        }
        return compositionService;
    }

    public List<Composition> getCompositions() {
        try {
            compositionList = compositionBackend.getCompositionList();
            return compositionList;
        } catch (HttpClientErrorException e) {
            return Collections.EMPTY_LIST;
        }
    }

    public Composition saveComposition(Composition composition) {
        return compositionBackend.createComposition(composition);
    }

    public void updateComposition(Composition composition) {
        compositionBackend.updateComposition(composition);
    }

    public void deleteComposition(Composition composition) {
        compositionBackend.deleteComposition(composition);
    }
}
