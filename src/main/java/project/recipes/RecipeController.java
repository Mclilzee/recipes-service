package project.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id - 1);
    }

    @PostMapping("/api/recipe/new")
    public Map<String, Integer> addRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }
}
