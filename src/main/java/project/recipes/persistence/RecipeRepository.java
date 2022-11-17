package project.recipes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recipes.business.recipes.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategoryIgnoreCase(String category);

    List<Recipe> findByNameContainingIgnoreCase(String name);
}
