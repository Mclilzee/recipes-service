package recipes.business.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipes.business.recipes.Recipe;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter @Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Recipe> recipes;

    public long addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        return this.recipes.size();
    }
}
