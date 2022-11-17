package project.recipes.presentation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import project.recipes.business.user.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

}
