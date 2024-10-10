package com.doza.coffee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientKey recipeIngredientKey;

    @ManyToOne
    @MapsId("recipeId")
    @JsonBackReference
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Ingredient ingredient;

    private int amount;

    public RecipeIngredient() {
    }



    public RecipeIngredientKey getRecipeIngredientKey() {
        return recipeIngredientKey;
    }

    public void setRecipeIngredientKey(RecipeIngredientKey recipeIngredientKey) {
        this.recipeIngredientKey = recipeIngredientKey;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeIngredientKey=" + recipeIngredientKey +
                ", recipe=" + recipe +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                '}';
    }
}
