package org.example.modules.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserModel userModel;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("User", new UserDTO());
        model.addAttribute("genderOptions", Arrays.asList("Male", "Female", "Other")); // Dùng Arrays.asList
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("User") UserDTO userDTO) throws SQLException {
        try {
            User user = new User();
            user.setUsername(userDTO.getUserName());
            user.setPassword(userDTO.getPassword());
            user.setAge(userDTO.getAge());
            user.setGender(userDTO.getGender());
            BeanUtils.copyProperties(user, userDTO);
            userModel.create(user);
            return "redirect:/user";
        } catch (Exception e) {
            return "redirect:/user";
        }
    }

    @GetMapping("/user")
    public String danhSachNguoiDung(Model model) throws SQLException {
        List<User> userList = userModel.findAll();
        model.addAttribute("userList", userList);
        return "user";
    }


}
