package com.doza.coffee.repository;

import com.doza.coffee.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
