package com.doza.coffee.service;

import com.doza.coffee.dto.IngredientDTO;
import com.doza.coffee.dto.RecipeDTO;
import com.doza.coffee.entity.Ingredient;
import com.doza.coffee.entity.Recipe;
import com.doza.coffee.entity.RecipeIngredient;
import com.doza.coffee.entity.RecipeIngredientKey;
import com.doza.coffee.repository.IngredientRepository;
import com.doza.coffee.repository.RecipeIngredientRepository;
import com.doza.coffee.repository.RecipeRepository;
import com.doza.coffee.util.CoffeeException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Transactional
    public Recipe createRecipe(RecipeDTO recipeDTO) {

        // Создание нового рецепта
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.getName());
        recipe = recipeRepository.save(recipe);

        // Добавление ингредиентов к рецепту
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getIngredientId())
                    .orElseThrow(() -> new CoffeeException("Ingredient not found: " + ingredientDTO.getIngredientId()));

            // Создание нового RecipeIngredient
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(ingredientDTO.getAmount());

            // Инициализация составного ключа с recipeId и ingredientId
            RecipeIngredientKey key = new RecipeIngredientKey(recipe.getId(), ingredient.getId());
            recipeIngredient.setRecipeIngredientKey(key);

            recipeIngredientRepository.save(recipeIngredient);
        }
        return recipe;
    }
}
