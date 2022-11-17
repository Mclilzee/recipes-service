package recipes.business.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.business.user.User;
import recipes.business.user.UserService;
import recipes.persistence.RecipeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    public Map<String, Long> addRecipe(UserDetails userDetails, Recipe recipe) {
        User user = userService.getUser(userDetails);
        recipe.setUserId(user.getId());

        recipe = recipeRepository.save(recipe);
        Map<String, Long> response = new HashMap<>();
        response.put("id", recipe.getId());
        return response;
    }

    public Recipe getRecipe(long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return recipe.get();
    }

    public void deleteRecipe(UserDetails userDetails, long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(userDetails);
        deleteUserRecipe(user, recipe.get());
    }

    private void deleteUserRecipe(User user, Recipe recipe) {
        if (!user.getRecipes().contains(recipe)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.delete(recipe);
    }

    public void updateRecipe(UserDetails userDetails, Recipe recipeToUpdate) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeToUpdate.getId());

        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(userDetails);
        updateUserRecipe(user, recipe);
    }

    private void updateUserRecipe(User user, Recipe recipe) {
        if (user.getId() != recipe.getUser().getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
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
