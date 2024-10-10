package com.doza.coffee.service;

import com.doza.coffee.entity.Coffee;
import com.doza.coffee.entity.Ingredient;
import com.doza.coffee.entity.Recipe;
import com.doza.coffee.entity.RecipeIngredient;
import com.doza.coffee.repository.CoffeeRepository;
import com.doza.coffee.repository.IngredientRepository;
import com.doza.coffee.repository.RecipeRepository;
import com.doza.coffee.util.CoffeeException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository, RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository) {
        this.coffeeRepository = coffeeRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Optional<Recipe> findRecipeById(long id) {
        return Optional.ofNullable(recipeRepository.findById(id)
                .orElseThrow(() -> new CoffeeException("Recipe not found")));
    }

    public List<Recipe> showAllCoffee() {
        return recipeRepository.findAll();
    }

    public Coffee makeCoffee(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new CoffeeException("Recipe not found"));

        // Проверка наличия ингредиентов
        for (RecipeIngredient ri : recipe.getIngredients()) {
            if (ri.getIngredient().getQuantity() < ri.getAmount()) {
                throw new CoffeeException("Not enough ingredient: " + ri.getIngredient().getName());
            }
        }

        // Списание ингредиентов
        for (RecipeIngredient ri : recipe.getIngredients()) {
            Ingredient ingredient = ri.getIngredient();
            ingredient.setQuantity(ingredient.getQuantity() - ri.getAmount());
            ingredientRepository.save(ingredient);
        }

        Coffee orderCoffee = new Coffee();
        orderCoffee.setRecipe(recipe);
        return coffeeRepository.save(orderCoffee);
    }

    public Recipe findMostPopular() {
        String query = "SELECT c.recipe FROM Coffee c GROUP BY c.recipe ORDER BY COUNT(c.recipe) DESC";
        return entityManager.createQuery(query, Recipe.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}

