package project.recipes.presentation.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.recipes.business.recipes.Recipe;
import project.recipes.business.recipes.RecipeService;

import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long> addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }
}
