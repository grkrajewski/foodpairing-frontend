package com.myapp.foodpairingfrontend.domain.dish;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularDish {

    @JsonProperty("id")
    private Long externalSystemId;

    @JsonProperty("title")
    private String name;

    @JsonProperty("readyInMinutes")
    private int readyInMinutes;

    @JsonProperty("servings")
    private int servings;

    @JsonProperty("sourceUrl")
    private String recipeUrl;
}
