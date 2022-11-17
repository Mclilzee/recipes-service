package project.recipes.presentation.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Map<String, Long> addRecipe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid Recipe recipe) {
        return recipeService.addRecipe(userDetails, recipe);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Object> deleteRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long id) {
        recipeService.deleteRecipe(userDetails, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody @Valid Recipe recipe, @PathVariable long id) {
        recipe.setId(id);
        recipeService.updateRecipe(userDetails, recipe);
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
