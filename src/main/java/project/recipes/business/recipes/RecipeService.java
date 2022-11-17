package project.recipes.business.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.recipes.persistence.RecipeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        recipeRepository.deleteById(id);
    }

    public void updateRecipe(Recipe recipe) {
        if (!recipeRepository.existsById(recipe.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        recipeRepository.save(recipe);
    }

    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCase(category).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Recipe> findRecipeByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name).stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
