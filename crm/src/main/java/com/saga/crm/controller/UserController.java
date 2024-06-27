package com.saga.crm.controller;

import com.saga.crm.dto.UserDto;
import com.saga.crm.model.User;
import com.saga.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);
        UserDto userDto = mapToUserDto(user);
        model.addAttribute("user", userDto);
        return "user/profile";
    }

    @PostMapping("/update")
    public String updateUserProfile(@ModelAttribute("user") UserDto userDto, Model model) {
        userService.updateUserProfile(userDto);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User updatedUser = userService.findUserByEmail(email);
        UserDto updatedUserDto = mapToUserDto(updatedUser);
        model.addAttribute("user", updatedUserDto);
        return "user/profile";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);

        if (!userService.isPasswordMatches(user, currentPassword)) {
            redirectAttributes.addFlashAttribute("error", "Senha atual incorreta");
            return "redirect:/user/profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Nova senha e confirmação de senha não coincidem");
            return "redirect:/user/profile";
        }

        userService.updatePassword(email, newPassword);
        redirectAttributes.addFlashAttribute("success", "Senha atualizada com sucesso");
        return "redirect:/user/profile";
    }

    @PostMapping("/delete")
    public String deleteUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findUserByEmail(email);
        userService.deleteUser(user.getId());
        return "redirect:/logout";
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword()); // Nota: Isso não é seguro, apenas para simplificação.
        return userDto;
    }
}