package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.UsersRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersPostController {

    @Autowired
    private final UsersRepository usersrepository;

    public UsersPostController(UsersRepository usersrepository) {
        this.usersrepository = usersrepository;
    }

    @GetMapping("/users")
    Iterable<Users> all() {
        return usersrepository.findAll();
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Optional<Users> findByIds(@PathVariable @NotNull Integer id) {

        return usersrepository.findById(id);
    }

    @PostMapping("/users")
    Users newUser(@RequestBody Users newUser) {
        return usersrepository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        usersrepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    Users updateUser(@RequestBody Users newUser, @PathVariable Integer id) {

        return usersrepository.findById(id).map(users -> {
            users.setId(newUser.getId());
            users.setName(newUser.getName());
            users.setEmail(newUser.getEmail());
            users.setPassword(newUser.getPassword());
            users.setRememberToken(newUser.getRememberToken());
            users.setCreated_at(newUser.getCreated_at());
            users.setUpdated_at(newUser.getUpdated_at());
            return usersrepository.save(newUser);
        }).orElseGet(() -> {
            newUser.setId(id);
            return usersrepository.save(newUser);
        });
    }

}
