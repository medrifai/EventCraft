package ma.eventcraft.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ma.eventcraft.models.User;
import ma.eventcraft.services.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Affiche le formulaire de login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Affiche le formulaire d'inscription
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // Traite le formulaire d'inscription
    @PostMapping("/signup")
    public String registerUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }

        // Vérifie si l'email est déjà utilisé
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email is already in use");
            return "signup";
        }

        // Encode le mot de passe et sauvegarde l'utilisateur
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);

        // Redirige vers le formulaire de login après inscription réussie
        return "redirect:/login";
    }
}
