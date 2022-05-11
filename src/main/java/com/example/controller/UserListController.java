package com.example.controller;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.domain.user.service.impl.UserServiceImpl2;
import com.example.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping ("/user" )
public class UserListController {
    @Autowired
    private UserService userService ;
    @Autowired
    private ModelMapper modelMapper ;

    /** Display user list screen */
    @GetMapping ("/list" )
    public String getUserList(@ModelAttribute UserListForm form , Model model ) {
// Convert form to MUser class
        MUser user = modelMapper .map(form , MUser.class );
// Get user list
        List<MUser> userList = userService .getUsers(user );
// Registered in Model
        model .addAttribute("userList" , userList );
// Display user list screen
        return "user/list" ;
    }

    /** User search process */
    @PostMapping("/list" )
    public String postUserList(@ModelAttribute UserListForm form , Model model ) {
// Convert form to MUser class
        MUser user = modelMapper.map(form , MUser.class );
// Get user list
        List<MUser> userList = userService .getUsers(user );
// Registered in Model
        model .addAttribute("userList" , userList );
// Display user list screen
        return "user/list" ;
    }
}