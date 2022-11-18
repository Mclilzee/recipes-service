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
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(userDetails);
        deleteUserRecipe(user, id);
    }

    private void deleteUserRecipe(User user, long id) {
        if (!userOwnsRecipe(user, id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.deleteById(id);
    }

    public void updateRecipe(UserDetails userDetails, Recipe newRecipe, long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUser(userDetails);
        newRecipe.setId(id);
        updateUserRecipe(user, newRecipe);
    }

    private void updateUserRecipe(User user, Recipe recipe) {
        if (!userOwnsRecipe(user, recipe.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipe.setUserId(user.getId());
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

    private boolean userOwnsRecipe(User user, long id) {
        return user.getRecipes().stream()
                .anyMatch(recipe -> recipe.getId() == id);
    }
}
