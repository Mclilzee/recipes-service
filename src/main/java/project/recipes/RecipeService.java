package project.recipes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    List<Recipe> recipesRepository = new ArrayList<>();


    public Map<String, Integer> save(Recipe recipe) {
        this.recipesRepository.add(recipe);

        Map<String, Integer> response = new HashMap<>();
        response.put("id", recipesRepository.size());
        return response;
    }

    public Recipe getRecipe(int id) {
        try {
            return recipesRepository.get(id);
        } catch (IndexOutOfBoundsException ex) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }
}
