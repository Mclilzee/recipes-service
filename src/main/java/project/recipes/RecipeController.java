package project.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return recipeService.getRecipe();
    }

    @PostMapping("/api/recipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.save(recipe);
    }
}
