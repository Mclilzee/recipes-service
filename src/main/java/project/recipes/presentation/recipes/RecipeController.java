package project.recipes.presentation.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.recipes.business.recipes.Recipe;
import project.recipes.business.recipes.RecipeService;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> addRecipe(@RequestBody @Valid Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@RequestBody @Valid Recipe recipe, @PathVariable long id) {
        recipe.setId(id);
        recipeService.updateRecipe(recipe);
        return ResponseEntity.noContent().build();
    }
}
