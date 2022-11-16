package project.recipes;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private Recipe recipe = new Recipe("", "", "", "");


    public Recipe save(Recipe recipe) {
        this.recipe = recipe;
        return recipe;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }
}
