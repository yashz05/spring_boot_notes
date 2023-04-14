package com.twp.ajavaproj.controller;


import com.twp.ajavaproj.entites.user;
import com.twp.ajavaproj.repo.userrepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
public class signup_controller {
    @Autowired
    userrepo usr;


    @RequestMapping("/signup")
    public ModelAndView index(@CookieValue(value = "logged",
            defaultValue = "false") String logged) {
        System.out.print(logged);
        if (logged.equals("false")) {
            return new ModelAndView("signup");
        } else {
            return new ModelAndView("redirect:/dashboard");
        }

    }

    @PostMapping("/signup/user")
    public ModelAndView login(@ModelAttribute user u, BindingResult r, HttpServletResponse response) {
        if (r.hasErrors()) {
            return new ModelAndView("error", "message", r.getAllErrors().toString());
        }
        user checkuser = usr.findByUsername(u.getUsername());
        if (checkuser != null) {
            return new ModelAndView("error", "message", "User Already Exist !");
        }
        usr.save(u);
        Cookie c1 = new Cookie("user", u.getUsername());
        Cookie c2 = new Cookie("logged", "true");
        // add cookie in server response
        c1.setPath("/");
        response.addCookie(c1);
        c2.setPath("/");
        response.addCookie(c2);
        return new ModelAndView("redirect:/dashboard");
    }

    ;


}
