package com.myapp.foodpairingfrontend.domain.drink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myapp.foodpairingfrontend.domain.drinkingredient.DrinkIngredient;
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
public class Drink {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("externalSystemId")
    private String externalSystemId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("alcoholic")
    private String alcoholic;

    @JsonProperty("glass")
    private String glass;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("drinkIngredientList")
    private List<DrinkIngredient> drinkIngredientList;
}
