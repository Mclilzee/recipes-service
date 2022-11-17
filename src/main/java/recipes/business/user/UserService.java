package recipes.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.persistence.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void createUser(User user) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(UserDetails userDetails) {
       Optional<User> user =  userRepository.findByEmailIgnoreCase(userDetails.getUsername());
       if (user.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }

       return user.get();
    }
}
