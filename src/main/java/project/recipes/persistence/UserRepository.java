package project.recipes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recipes.business.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}