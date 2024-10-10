package com.doza.coffee.controller;

import com.doza.coffee.dto.RecipeDTO;
import com.doza.coffee.entity.Recipe;
import com.doza.coffee.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);


    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create new recipe", description = "Post request to create a new recipe.")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {

        log.info("Creating Recipe with name: {}", recipeDTO.getName());
        recipeDTO.getIngredients().forEach(ingredientDTO ->
                log.info("Ingredient ID: {}, Amount: {}", ingredientDTO.getIngredientId(), ingredientDTO.getAmount())
        );
        Recipe createdRecipe = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }
}

