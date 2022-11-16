package project.recipes.business.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@NoArgsConstructor
@Getter @Setter
public class RecipeDirection {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String direction;

    public RecipeDirection(String direction) {
        this.direction = direction;
    }
}
