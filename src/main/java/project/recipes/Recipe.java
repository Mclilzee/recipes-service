package project.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Recipe {

    private String name;
    private String description;
    private String[] ingredients;
    private String[] directions;
}
