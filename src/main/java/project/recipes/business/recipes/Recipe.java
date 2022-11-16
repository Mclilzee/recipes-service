package project.recipes.business.recipes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredient> ingredients;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<RecipeDirection> directions;

    @JsonCreator
    public Recipe(String name, String description, String[] ingredients, String[] directions) {
        this.name = name;
        this.description = description;
        this.ingredients = Arrays.stream(ingredients)
                .map(RecipeIngredient::new)
                .collect(Collectors.toList());

        this.directions = Arrays.stream(directions)
                .map(RecipeDirection::new)
                .collect(Collectors.toList());
    }
}