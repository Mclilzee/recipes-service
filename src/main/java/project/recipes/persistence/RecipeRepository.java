package project.recipes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recipes.business.recipes.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
