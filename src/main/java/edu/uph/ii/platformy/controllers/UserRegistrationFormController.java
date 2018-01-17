package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.services.UserService;
import edu.uph.ii.platformy.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SessionAttributes("User")
@Controller
public class UserRegistrationFormController {
    @Autowired
    private UserService userService;

    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }
    @ModelAttribute("User")
    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        userService.save(userForm);
        return "registrationSuccess";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }

}