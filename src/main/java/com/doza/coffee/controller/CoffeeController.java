package com.doza.coffee.controller;

import com.doza.coffee.entity.Recipe;
import com.doza.coffee.service.CoffeeService;
import com.doza.coffee.util.CoffeeException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/showAll")
    @Operation(summary = "Get all coffees", description = "Returns a list of all available coffees.")
    public List<Recipe> showAllCoffee() {
        return coffeeService.showAllCoffee();
    }

    @PostMapping("/order/{recipeId}")
    @Operation(summary = "Take order coffee", description = "Making coffee by ID.")
    public ResponseEntity<String> makeOrderCoffee(@PathVariable Long recipeId) {
        Recipe recipe = coffeeService.findRecipeById(recipeId)
                .orElseThrow(() -> new CoffeeException("Recipe not found"));

        coffeeService.makeCoffee(recipeId);

        return new ResponseEntity<>("Coffee is ready!", HttpStatus.OK);
    }

    @GetMapping("/popular")
    @Operation(summary = "Most popular coffee", description = "Brings back the most popular drink.")
    public Recipe findPopularOrder() {
        return coffeeService.findMostPopular();
    }
}
