package org.example.controller;
//РЕАЛИЗОВАТЬ ФОРМУ УДАЛЕНИЯ ПОЛЬЗОВАТЕЛЯ
import org.example.dto.User;
import org.example.dto.UserFormData;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@Controller
@RequestMapping(path = "/view")
public class ViewController {
    private final UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{userId}")
    public String getProfileView(@PathVariable("userId") long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("id", user.id());
        model.addAttribute("firstName", user.firstName());
        model.addAttribute("lastName", user.lastName());
        model.addAttribute(
                "age",
                user.birthDate() == null ? "" : Period.between(user.birthDate(), LocalDate.now()).getYears()
        );
        model.addAttribute("email", user.email() != null ? user.email() : "");
        return "user_profile";
    }

    @GetMapping("register")
    public String getRegisterForm(Model model) {
        model.addAttribute("userForm", new UserFormData());
        return "user_registration";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("userForm") UserFormData userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_registration_error";
        }
        User newUser = userService.createUser(userForm.toUser());
        model.addAttribute("userForm", userForm);
        model.addAttribute("newUser", newUser);
        return "user_registered";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    @GetMapping("/profile/{userId}/edit")
    public String getEditProfileForm(@PathVariable("userId") long userId, Model model) {
        User user = userService.getUser(userId);
        UserFormData userFormData = new UserFormData();
        userFormData.setFirstName(user.firstName());
        userFormData.setLastName(user.lastName());
        userFormData.setBirthDate(user.birthDate());
        userFormData.setEmail(user.email());
        model.addAttribute("editForm", userFormData);
        model.addAttribute("userId", userId);
        return "user_profile_edit";
    }

    @PutMapping("/profile/{userId}/edit")
    public String editProfile(
            @PathVariable("userId") long userId,
            @ModelAttribute("editForm") UserFormData userForm,
            BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            return "user_profile_edit_error";
        }
        User updatedUserDto = userService.updateUser(userId, userForm.toUser());
        model.addAttribute("updatedUser", updatedUserDto);
        return "user_profile_edit_success";
    }

    @GetMapping("/profile/{id}/delete")
    public String getDeleteForm(@PathVariable ("id") long id, Model model){
    User user = userService.getUser(id);
    model.addAttribute("deletedUser",user);
    return "user_delete";
    }

    @DeleteMapping("/profile/{id}/delete")
    public String deleteUser(@PathVariable ("id") long id){
      userService.deleteUser(id);

        return "user_delete_success";
    }
    @GetMapping("/profile/{userId}/status")
    public String getStatusForm(@PathVariable("userId") long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("userId", userId);
        return "user_status";
    }
    @PostMapping("/profile/{userId}/status")
    public String updateStatus(@PathVariable("userId") long userId, @RequestParam("status") String status) {
        User user = userService.getUser(userId);
        User updatedUser = new User(user.id(), user.firstName(), user.lastName(), user.birthDate(), user.email(), status);
        userService.updateUser(userId, updatedUser);
        return "redirect:/view/profile/" + userId;
    }
}
