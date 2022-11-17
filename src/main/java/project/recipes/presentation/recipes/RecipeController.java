package project.recipes.presentation.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.recipes.business.recipes.Recipe;
import project.recipes.business.recipes.RecipeService;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping(value = "/api/recipe/search", params = {"category"})
    public List<Recipe> getRecipeByCategory(@RequestParam String category) {
        return recipeService.findRecipesByCategory(category);
    }

    @GetMapping(value = "/api/recipe/search", params = {"name"})
    public List<Recipe> getRecipeByName(@RequestParam String name) {
        return recipeService.findRecipeByName(name);
    }

    @GetMapping(value = "/api/recipe/search", params = {"name", "category"})
    public List<Recipe> getRecipeByNameAndCategory(@RequestParam String name, @RequestParam String category) {
        return getRecipeByName(name);
    }
}
