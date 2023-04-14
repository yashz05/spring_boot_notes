package com.twp.ajavaproj.controller;

import com.twp.ajavaproj.entites.user;
import com.twp.ajavaproj.repo.userrepo;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RestController
public class login_controller {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    userrepo usr;


    @RequestMapping("/")
    public ModelAndView index(@CookieValue(value = "logged",
            defaultValue = "false") String logged) {
        System.out.print(logged);
        if (logged.equals("false")) {
            return new ModelAndView("home");
        } else {
            return new ModelAndView("redirect:/dashboard");
        }

    }

    @PostMapping("/login/user")
    public ModelAndView login(@ModelAttribute user u,  BindingResult r, HttpServletResponse response) {
        if (r.hasErrors()) {
            return new ModelAndView("error", "message", r.getAllErrors().toString());
        }
        user checkuser = usr.findByUsername(u.getUsername());
        if (checkuser == null) {
            return new ModelAndView("error", "message", "User Not Found");
        }
        if(!checkuser.getPassword().equals(u.getPassword())){
            return new ModelAndView("error", "message", "Password Not Match !");
        }
        Cookie c1 = new Cookie("user", u.getUsername());
        Cookie c2 = new Cookie("logged", "true");
        // add cookie in server response
        c1.setPath("/");
        response.addCookie(c1);
        c2.setPath("/");
        response.addCookie(c2);
        return new ModelAndView("redirect:/dashboard");

    };

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        Cookie c1 = new Cookie("logged", null);
        Cookie c2 = new Cookie("user", null);
        c1.setMaxAge(0); // delete cookie
        c2.setMaxAge(0); // delete cookie
        response.addCookie(c1);
        response.addCookie(c2);
        return new ModelAndView("redirect:/");
    }


}
