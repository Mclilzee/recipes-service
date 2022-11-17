package project.recipes.business.recipes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({ "name", "date", "category", "description", "ingredients", "directions"})
public class Recipe implements Comparable<Recipe> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime date;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @ElementCollection
    @OrderColumn
    @NotEmpty
    private String[] ingredients;

    @ElementCollection
    @OrderColumn
    @NotEmpty
    private String[] directions;

    @JsonCreator
    public Recipe(String name, String category, String description, @NotNull String[] ingredients, @NotNull String[] directions) {
        this.name = name;
        this.category = category;
        this.date = LocalDateTime.now();
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    @Override
    public int compareTo(Recipe o) {
        return this.date.compareTo(o.date);
    }
}
