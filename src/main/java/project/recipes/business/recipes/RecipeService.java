package project.recipes.business.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.recipes.persistence.RecipeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Map<String, Long> addRecipe(Recipe recipe) {
        recipe = this.recipeRepository.save(recipe);

        Map<String, Long> response = new HashMap<>();
        response.put("id", recipeRepository.save(recipe).getId());
        return response;
    }

    public Recipe getRecipe(long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return recipe.get();
    }
}
