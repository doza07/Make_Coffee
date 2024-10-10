package com.doza.coffee.repository;

import com.doza.coffee.entity.RecipeIngredient;
import com.doza.coffee.entity.RecipeIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientKey> {

    List<RecipeIngredient> findByRecipeId(Long recipeId);
}

