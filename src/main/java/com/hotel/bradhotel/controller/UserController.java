package com.hotel.bradhotel.controller;

import com.hotel.bradhotel.dto.UserLoginRequest;
import com.hotel.bradhotel.dto.UserRegisterRequest;
import com.hotel.bradhotel.model.User;
import com.hotel.bradhotel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    //註冊會員
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest,
                                         HttpSession session){
        Integer userId= userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        session.setAttribute("loginEmail", user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //登入會員

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest,
                                      HttpSession session){

      User user =  userService.login(userLoginRequest);

      session.setAttribute("loginEmail",user);

      return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    //登出
    @GetMapping("/logout")
    public  String logut(HttpSession session, SessionStatus sessionStatus){

        if (session.getAttribute("loginEmail") != null){
            log.info(session.getAttribute("loginEmail").toString() +"登出系統");
            session.removeAttribute("loginEmail");

            sessionStatus.setComplete();
        }

        return "redirect:";
    }


    @PostMapping("/user/checkEmail")
    public ResponseEntity<User> checkEamil(@RequestBody UserRegisterRequest userRegisterRequest){
        User user = userService.getUserByEmail(userRegisterRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping("/home/checklogin")
    public ResponseEntity<User> checkLogin(HttpSession session){
        User getEmail = (User) session.getAttribute("loginEmail");

        if (getEmail != null){

            return ResponseEntity.status(HttpStatus.OK).body(getEmail);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getEmail);
        }
    }

}
