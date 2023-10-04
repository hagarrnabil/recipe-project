package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.models.Unit;
import guru.springframework.recipeproject.models.Users;
import guru.springframework.recipeproject.repositories.PasswordResets;
import guru.springframework.recipeproject.repositories.UnitRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordResetsPostController {


    private final PasswordResets passwordresets;

    public PasswordResetsPostController(PasswordResets passwordresets) {
        this.passwordresets = passwordresets;
    }

    @GetMapping("/passwordresets")
    Iterable<guru.springframework.recipeproject.models.PasswordResets> all() {
        return passwordresets.findAll();
    }


    @PostMapping("/passwordresets")
    guru.springframework.recipeproject.models.PasswordResets newPasswordResets(@RequestBody guru.springframework.recipeproject.models.PasswordResets
                                                                               newPasswordResets) {
        return passwordresets.save(newPasswordResets);
    }

    @DeleteMapping("/passwordresets/{token}")
    void deletePasswordreset(@PathVariable Integer token) {
        passwordresets.deleteById(token);
    }

    @PutMapping("/passwordreset/{token}")
    guru.springframework.recipeproject.models.PasswordResets
    updatePasswordreset(@RequestBody guru.springframework.recipeproject.models.PasswordResets newPasswordreset
            , @PathVariable Integer token) {

        return passwordresets.findById(token).map(passwordResets -> {
            passwordResets.setToken(newPasswordreset.getToken());
            passwordResets.setEmail(newPasswordreset.getEmail());
            passwordResets.setCreated_at(newPasswordreset.getCreated_at());
            passwordResets.setUpdated_at(newPasswordreset.getUpdated_at());
            return passwordresets.save(newPasswordreset);
        }).orElseGet(() -> {
            newPasswordreset.setToken(token);
            return passwordresets.save(newPasswordreset);
        });
    }

}