package com.myapp.foodpairingfrontend.domain.composition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myapp.foodpairingfrontend.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Composition {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("dishId")
    private Long dishId;

    @JsonProperty("drinkId")
    private Long drinkId;

    @JsonProperty("created")
    private String created;

    @JsonProperty("commentList")
    private List<Comment> commentList;
}
