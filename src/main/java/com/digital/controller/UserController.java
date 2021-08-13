package com.digital.controller;
import com.digital.entity.User;
import com.digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // get all user
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }





    @RequestMapping(value = "editProfile", method = RequestMethod.PUT)
    public String editProfilePage(User user)  {


        return "editProfile";
    }
}
