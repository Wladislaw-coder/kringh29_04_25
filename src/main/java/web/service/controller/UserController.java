package web.service.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.service.service.UserService;
import web.service.user.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "show";
    }

    @GetMapping("/new")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "form";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "User added successfully");
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(value = "id",required = true) long id, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findById(id);
        if (user == null) {
            redirectAttributes.addFlashAttribute("flashMessage", "User not found");
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "form";

    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

}
